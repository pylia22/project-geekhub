<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cabinet</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
<br/>

<div class="btn-group-vertical">
    <button  type="button" onclick="location.href = '/event/analytic'" class="btn btn-secondary" style="color: white">Top Sold Events</button>
    <button type="button" onclick="location.href = '/event/analytic/last-month-orders'" class="btn btn-secondary" style="color: white">Last Month Bookings</button>
</div>

<h2 align="center">Top Sold Events</h2>

<br/>

<div class="container">

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Event Name</th>
            <th scope="col">City</th>
            <th scope="col">Tickets Sold</th>
            <th scope="col">Ticket Price</th>
            <th scope="col">Amount Earn</th>
            <th scope="col"><a href="/event/analytic/export">Export</a></th>
        </tr>
        </thead>
        <tbody>

        <#list orders as order>
            <tr>
                <td>${order.event.eventName}</td>
                <td>${order.event.location.city}</td>
                <td>${order.quantity}</td>
                <td>${order.event.price}</td>
                <td>${order.event.price * order.quantity}</td>
            </tr>
        </#list>

        </tbody>
    </table>

    <br/>
    <br/>

</div>

</body>
</html>


