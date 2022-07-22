<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Update Event</title>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container">
        <a href="#"
           class="navbar-brand mb-0 h1">
            <img class="d-inline-block"
                 src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg"
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
                <li class="nav-item active">
                    <a href="/event/analytic" class="nav-link">
                        Analytic
                    </a>
                </li>
                <li class="nav-item active">
                    <a href="/logout" class="nav-link">
                        Logout
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="card">
    <div class="card-header">
        <p class="h4 mb-2 text-center">Edit Event</p>
    </div>
    <div class="card-body">
        <form class="text-center border border-light p-5" action="${'/event/edit/' + event.id}" name="event"
              method="POST" enctype="multipart/form-data">

            <input type="text" name="eventName" id="eventName" value="${(event.eventName)!''}" class="form-control mb-7"
                   placeholder="Product Name">
            <br/>

            <select name="type" id="type" class="form-control mb-4"
                    placeholder="Category">

                <option value=Sport>Sport</option>
                <option value=Concert>Concert</option>
                <option value=Theatre>Theatre</option>
                <option value=Children>Children</option>
                <option value=Adventure>Adventure</option>
                <option value=Show>Show</option>

            </select>

            <textarea id="description" name="description" class="form-control mb-4"
                      placeholder="Product Description">${(event.description)!''}</textarea>

            <input type="datetime-local" name="date" id="date" value="${(event.date)!''}" class="form-control mb-4"
                   placeholder="Date">

            <select name="city" id="city" class="form-control mb-4"
                    placeholder="City">
                <#list cities as city>
                    <option value=${city.getCity()}>${city.getCity()}</option>
                </#list>
            </select>


            <input type="text" name="price" id="price" value="${(event.price)!''}" class="form-control mb-4"
                   placeholder="Product Price">

            <div class="custom-file mb-4">
                <input type="file" name="image" class="custom-file-input"
                       id="customFile"> <label class="custom-file-label"
                                               for="customFile">Event Image</label>
            </div>

            <input type="submit" value="Update"/>
        </form>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="/js/jquery-3.6.0.min.js"></script>
<script src="/js/drop-down.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>