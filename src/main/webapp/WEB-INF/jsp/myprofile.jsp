
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile</title>
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand ms-3" href="home">
        <img class="ms-3" src="https://i.postimg.cc/f3VFM2Q0/book.png" alt="book logo" width="60" height = "60">
        Book Exchange
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link" href="home">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link   active" href="myprofile">My Profile</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="mybooks">My Books</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="myrequests">My Requests</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="signout">Logout</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="admin">Admin Controls</a>
          </li>
        </ul>
      </div>
    </div>
</nav>   
    <section class="vh-100" style="background-color: #f4f5f7;">
        <div class="container py-5 h-100">
          <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-lg-6 mb-4 mb-lg-0">
              <div class="card mb-3" style="border-radius: .5rem;">
                <div class="row g-0">
                  <div class="col-md-4 gradient-custom text-center text-white" style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
                    <img
                      src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                      alt="Avatar"
                      class="img-fluid my-5"
                      style="width: 80px;"
                    />
                    <h5>
                        <p style="color:rgb(0, 0, 0)">${user.name}</p>
                    </h5>
                    <p style="color:rgb(0, 0, 0)">Discription</p>
                    <i class="far fa-edit mb-5"></i>
                  </div>
                  <div class="col-md-8">
                    <div class="card-body p-4">
                      <h6>Information</h6>
                      <hr class="mt-0 mb-4">
                      <div class="row pt-1">
                        <div class="col-6 mb-3">
                          <h6>Email</h6>
                          <p class="text-muted">${user.email}</p>
                        </div>
                        <div class="col-6 mb-3">
                          <h6>Phone</h6>
                          <p class="text-muted">${user.phoneNumber}</p>
                        </div>
                      </div>
                      <h6>Wallet Information</h6>
                      <hr class="mt-0 mb-4">
                      <div class="row pt-1">
                        <div class="col-6 mb-3">
                          <h6>Amount Available</h6>
                          <p class="text-muted">INR: ${user.wallet}</p>
                        </div>
                        <div class="col-6 mb-3">
                            <button type="button" class="btn btn-dark btn-sm" style="text-align:center; "><p style="color:rgb(255, 255, 255)"><a href = "addmoney"> Add Money</a></p> </button>
                        </div>
                      </div>
                      
                      <button type="button" class="btn btn-dark btn-sm"><a href = "edituser">Edit Profile<a></button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>