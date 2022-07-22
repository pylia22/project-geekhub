<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cabinet</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<h1 align="center">User's Booking History</h1>

<br/>

<div class="container">

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Event Name</th>
            <th scope="col">Event Location</th>
            <th scope="col">Event Date</th>
            <th scope="col">Event Price</th>
            <th scope="col">Tickets Booked</th>
            <th scope="col">Booked Under</th>
        </tr>
        </thead>
        <tbody>

        <#list orders as order>
            <tr>
                <td>${order.getEvent().eventName}</td>
                <td>${order.getEvent().getLocation().city}</td>
                <td>${order.getEvent().getDate()}</td>
                <td>${order.getEvent().getPrice()}</td>
                <td>${order.getQuantity()}</td>
                <td>${order.getUser().firstName + " " + order.getUser().lastName}</td>
                <td><a href="/event/order/refund/${order.id}">Ticket Refund</a></td>
            </tr>
        </#list>

        </tbody>
    </table>

    <br/>

    <a href="/event"><h3 align="center" style="color: darkblue">Home Page</h3></a>

</div>

</body>
</html>


