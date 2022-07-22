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

<div class="container" style="margin-top: 8%; margin-left: 8%">
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <#list events as event>
            <div class="col-4-lg">
                <div class="card">
                    <img src="data:image/jpg;base64,${event.image}" style="width: auto; height: 300px;">
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
                            <a href="event/${event.id}" class="btn btn-primary">Read More</a>

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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="/js/jquery-3.6.0.min.js"></script>
<script src="/js/drop-down.js"></script>
<script src="/js/load-more.js"></script>
<script src="/js/hover.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>