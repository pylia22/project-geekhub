<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Secured Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<section class="vh-100 bg-image"
         style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
    <div class="mask d-flex align-items-center h-100 gradient-custom-3">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                    <div class="card" style="border-radius: 15px;">
                        <div class="card-body p-5">
                            <h2 class="text-uppercase text-center mb-5">Create an account</h2>

                            <div class="container">
                                <#if errors??>
                                    <#list errors as error>
                                        <p style="color: red">${error.defaultMessage}</p>
                                    </#list>
                                </#if>

                                <form action="/registration" method="post" name="registration">

                                    <div class="form-outline mb-4">

                                        <input type="text" id="form3Example1cg" name="firstName"
                                               class="form-control form-control-lg"/>
                                        <label class="form-label" for="form3Example1cg">First Name</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="text" id="form3Example1cg" name="lastName"
                                               class="form-control form-control-lg"/>
                                        <label class="form-label" for="form3Example1cg">Last Name</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="email" id="form3Example3cg" name="email"
                                               class="form-control form-control-lg"/>
                                        <label class="form-label" for="form3Example3cg">Your Email</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="text" id="form3Example1cg" name="login"
                                               class="form-control form-control-lg"/>
                                        <label class="form-label" for="form3Example1cg">Login</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="password" id="form3Example4cg" name="password"
                                               class="form-control form-control-lg"/>
                                        <label class="form-label" for="form3Example4cg">Password</label>
                                    </div>

                                    <div class="d-flex justify-content-center">
                                        <button type="submit"
                                                class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">
                                            Register
                                        </button>
                                    </div>

                                    <p class="text-center text-muted mt-5 mb-0">Have already an account? <a
                                                href="/login"
                                                class="fw-bold text-body"><u>Login here</u></a></p>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>


