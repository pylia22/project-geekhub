<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/style4.css">

    <title>What's On Ukraine</title>
</head>
<body>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container">
        <a href="#"
           class="navbar-brand mb-0 h1">
            <img class="d-inline-block"
                 src="https://getbootstrap.com/docs/4.0/
assets/brand/bootstrap-solid.svg"
                 width="30" height="30"/>
            What's On Ukraine
        </a>
        <button
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarNav"
                class="navbar-toggler"
                aria-controls="navbarNav"
                aria-expanded="false"
                aria-label="Toggle navigation">

            <span class="navbar-toggler-icon"></span>
        </button>
        <div
                class="collapse navbar-collapse"
                id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a href="/event" class="nav-link active">
                        Home
                    </a>
                </li>
                <#if admin>
                    <li class="nav-item active">
                        <a href="/event/analytic" class="nav-link">
                            Analytic
                        </a>
                    </li>
                    <#else>
                        <li class="nav-item active">
                            <a href="/event/cabinet" class="nav-link">
                                Cabinet
                            </a>
                        </li>
                </#if>
                <li class="nav-item active">
                    <a href="/logout" class="nav-link">
                        Logout
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<br/>
<br/>

<div class="container">

    <form action="/event/${id}" method="post">

        <div style="display:inline-block;vertical-align:top;">
            <img src="data:image/jpg;base64,${event.image}" style="width: auto; height: 500px;">
        </div>
        <div style="display:inline-block; padding-left: 50px">
            <p>
            <h1>${event.eventName}</h1>
            </p>
            <p>
            <h4>${event.date.toLocalDate() + " " + event.date.getHour() + ":" + event.date.getMinute()}</h4>
            </p>
            <p>
            <h4>${event.location.city + ", Ukraine"}</h4>
            </p>
            <p>
            <h4>${event.description}</h4>
            </p>
            <br/>
            <p>
            <h4>Price: ${event.price + " USD"}</h4>
            </p>

            <#if !admin>
                <p>
                    <button onclick="myFunction()" id="parent" type="button" class="btn btn-primary col-6">Buy</button>
                <div id="myDIV">
                    <input type="number" name="quantity" id="quantity" class="form-control mb-4"
                           placeholder="quantity">
                    <a href="/event/${event.id}"<input type="submit" value="Confirm"/>
                    </a>
                </div>
                </p>
            </#if>

        </div>
    </form>
</div>

<br/>

<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-5 col-md-6 col-12 pb-4">
                <h1>Comments</h1>

                <#list feedbacks as feedback>
                    <div class="comment mt-4 text-justify float-left">
                        <h4>${feedback.userName}</h4>
                        <span>${feedback.date.toLocalDate() + " " + feedback.date.getHour() + ":" + feedback.date.getMinute()}</span>
                        <br>
                        <p>${feedback.message}</p>

                        <#if admin>
                        <a href="/event/${feedback.eventId}/feedback/${feedback.id}/delete" name="feedbackId" id="feedbackId" class="btn btn-primary">Delete</a>
                        </#if>
                    </div>
                </#list>

            </div>
            <div class="col-lg-4 col-md-5 col-sm-4 offset-md-1 offset-sm-1 col-12 mt-4">
                <form action="/event/${id}/feedback" method="post" id="algin-form">
                    <div class="form-group">
                        <h4>Leave a comment</h4>
                        <label for="message">Message</label>
                        <textarea name="message" id="message" msg cols="30" rows="5" class="form-control"
                                  style="background-color: white;"></textarea>
                    </div>

                    <br>
                    <div class="form-group">
                        <button id="child" type="submit" class="btn btn-primary col-6">Post Comment</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>


<br/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="/js/jquery-3.6.0.min.js"></script>
<script src="/js/drop-down.js"></script>
<script src="/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>


<#--
<#if event??>
    <table border="0">
        <tr>
            <td>Name</td>
            <td>:</td>
            <td>${event.eventName}</td>
        </tr>
        <tr>
            <td>Type</td>
            <td>:</td>
            <td>${event.type}</td>
        </tr>
        <tr>
            <td>Description</td>
            <td>:</td>
            <td>${event.description}</td>
        </tr>
        <tr>
            <td>Date</td>
            <td>:</td>
            <td>${(event.date)}</td>
        </tr>
        <tr>
            <td>City</td>
            <td>:</td>
            <td>${(event.location.city)}</td>
        </tr>
        <tr>
            <td>Price</td>
            <td>:</td>
            <td>${(event.price)}</td>
        </tr>
    </table>
    <br/><br/>

    <div>
        <a href="${'/event/' + event.id + '/edit'}">Edit</a> |
        <a href="${'/event/' + event.id + '/delete'}">Delete</a>
    </div>

</#if>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="/js/jquery-3.6.0.min.js"></script>
<script src="/js/drop-down.js"></script>

</body>
</html>-->
