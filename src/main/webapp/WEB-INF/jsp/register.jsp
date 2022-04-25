
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Book Exchange</title>
    <style>
      .btn{
        border: none;
        outline: none;
        height: 50px;
        width: 100%;
        background-color: #703f37;
        color: white;
        border-radius: 4px;
        font-weight: bold;
      }
      .btn:hover{
        background: white;
        border: 1px solid;
        color: #703f37;
      }
    </style>
  </head>
  <body>
    <section class="vh-100" style="background-color: #c8b7a6;">
        <div class="container py-5 h-100">
          <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-xl-10">
              <div class="card" style="border-radius: 1rem;">
                <div class="row g-0">
                  <div class="col-md-6 col-lg-5 d-none d-md-block">
                    <img src='https://i.postimg.cc/KjLqQgGF/books-login-1-min.jpg' alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;"/>
                  </div>
                  <div class="col-md-6 col-lg-7 d-flex align-items-center">
                    <div class="card-body p-4 p-lg-5 text-black">
      
                      <form action = "login" method="post">
      
                        <div class="d-flex align-items-center mb-3 pb-1 pt-1">
                          <i class="fas fa-cubes fa-2x me-3" style="color: #703f37;">
                            <span class="h1 fw-bold mb-0">Book Exchange</span>
                          </i>
                        </div>
      
                        <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Create your account</h5>

                        <div class="form-floating mb-4">
                            <input name = "name" type="text" id="form3Example1cg" class="form-control form-control-lg" placeholder="Your Name" />
                            <label class="form-label" for="form3Example1cg">Your Name</label>
                        </div>
      
                        <div class="form-floating mb-4">
                          <input name = "email" type="email" id="form2Example17" class="form-control form-control-lg" placeholder="name@example.com"/>
                          <label class="form-label" for="form2Example17">Email address</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input name = "phoneNumber" type="number" id="form2Example37" class="form-control form-control-lg" placeholder="xxxxxxxxxx"/>
                            <label class="form-label" for="form2Example37">Phone Number</label>
                          </div>
                          <div class="form-floating mb-4">
                            <input name = "address" type="text" id="form3Example2cg" class="form-control form-control-lg" placeholder="Your Address" />
                            <label class="form-label" for="form3Example2cg">Your Address</label>
                        </div>
      
                        <div class="form-floating mb-4">
                          <input name = "password" type="password" id="form2Example27" class="form-control form-control-lg" placeholder="password"/>
                          <label class="form-label" for="form2Example27">Password</label>
                        </div>

                        <!-- <div class="form-check mb-4">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                            <label class="form-check-label" for="flexCheckDefault">
                              Admin
                            </label>
                        </div> -->
      
                        <div class="pt-1 mb-4">
                          <input class="btn btn-dark btn-lg btn-block" type="submit">Register</button>
                        </div>
      
                        <p class="mb-3 pb-lg-2" style="color: #393f81;">Already have an account? <a href="login"
                            style="color: #393f81;">Login here</a></p>
                      </form>
      
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>


    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
  
  </body>
</html>