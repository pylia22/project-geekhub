<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/style4.css">

    <title>What's On Ukraine</title>
    <style>
        .center {
            margin: auto;
            width: 10%;
            padding: 10px;
        }
    </style>
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
            What's on Ukraine
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
                <li class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle"
                       id="navbarDropdown" role="button"

                       data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Categories
                    </a>
                    <ul class="dropdown-menu"
                        aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="/event/category/concert">Concerts</a></li>
                        <li><a class="dropdown-item" href="/event/category/show">Shows</a></li>
                        <li><a class="dropdown-item" href="/event/category/theatre">Theatres</a></li>
                        <li><a class="dropdown-item" href="/event/category/sport">Sport</a></li>
                        <li><a class="dropdown-item" href="/event/category/children">Children</a></li>
                        <li><a class="dropdown-item" href="/event/category/adventure">Adventures</a></li>
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle"
                       id="navbarDropdown" role="button"

                       data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Cities
                    </a>
                        <ul class="dropdown-menu"
                            aria-labelledby="navbarDropdown">
                            <#if locations??>
                            <#list locations as l>
                            <li><a class="dropdown-item" href="/event/city/${l?lower_case}">${l}</a></li>
                            </#list>
                            </#if>
                        </ul>
                </li>

                <#if admin>
                    <li class="nav-item active">
                        <a href="/event/add" class="nav-link">
                            Add New
                        </a>
                    </li>
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

        <form method="GET" action="/event/search" class="my-3">
            <div class="input-group">
                <input name="keyWord" placeholder="Search" class="form-control"/>
                <div class="input-group-append">
                    <input type="submit" value="Search" class="btn btn-outline-primary"/>
                </div>
            </div>
        </form>
    </div>
</nav>

<br/>
<br/>
<div id="showMore-list">

    <style>
        .card:hover {
            -webkit-box-shadow: -1px 9px 40px -12px rgb(0, 0, 0, 0.75);
            -moz-box-shadow: -1px 9px 40px -12px rgb(0, 0, 0, 0.75);
            box-shadow: -1px 9px 40px -12px rgb(0, 0, 0, 0.75);
        }
    </style>

    <div class="container" style="margin-top: 8%; margin-left: 8%">
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <#list events as event>
                <div class="col-4-lg">
                    <div class="card">
                        <img src="
                        data:image/jpg;base64,${event.image}" style="width: auto; height: 300px;">
                        <div class="card-body" align="center">
                            <a href="/event/${event.getId()}" rel="link"><h5 class="card-title">${event.eventName}</h5>
                            </a>
                            <p class="card-text">
                                ${event.description}
                            </p>
                            <p class="card-date">
                                ${event.date.toLocalDate() + " " + event.date.getHour() + ":" + event.date.getMinute() + " " + event.getLocation().getCity()}
                            </p>
                            <p class="card-price">
                                Price: ${event.price} USD
                            </p>
                            <div align="center">
                                <a href="/event/${event.id}" class="btn btn-primary">Read More</a>

                                <#if admin>
                                    <a href="/event/edit/${event.id}" class="btn btn-primary">Edit</a>
                                    <a href="/event/delete/${event.id}" class="btn btn-primary">Delete</a>
                                </#if>

                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</div>

<br/>

<div class="center">
    <button type="button" class="btn btn-primary" id="load-more">Load More</button>
</div>

<br/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="/js/jquery-3.6.0.min.js"></script>
<script src="/js/drop-down.js"></script>
<script src="/js/load-more.js"></script>
<script src="/js/hover.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>
