
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
      <section style="background-color: #eee;">
        <form method="post" action = "addtowallet">
        <h1 style="text-align: center;color: black;" >Add Money To Wallet</h1>
        <h3 style="text-align: center;color: black;" >Minimum Amount to borrow a book is 30</h3>
        <div class="container py-5">
          <div class="row d-flex justify-content-center">
            <div class="col-md-8 col-lg-6 col-xl-4">
              <div class="card rounded-3">
                <div class="card-body mx-1 my-2">
                  <div class="d-flex align-items-center">
                    <div>
                      <i class="fab fa-cc-visa fa-4x text-black pe-3"></i>
                    </div>
                    <div>
                      <p class="d-flex flex-column mb-0">
                        <b>${user.name}</b><span class="small text-muted">${user.email}</span>
                      </p>
                    </div>
                  </div>
      
                    <div class="d-flex flex-row pb-3">
                      <div class="rounded border d-flex w-100 px-3 py-2 align-items-center">
                        <div class="d-flex align-items-center pe-3">
                          <input class="form-check-input" type="radio" name="radioNoLabelX" id="radioNoLabel22"
                            value="" aria-label="..." />
                        </div>
                        <div class="d-flex flex-column py-1">
                          <p class="mb-1 small" style="text-align: center;color: rgb(0, 0, 0);">Amount Required</p>
                          <div class="d-flex flex-row align-items-center">
                            <h6 class="mb-0 pe-1" style="text-align: center;color: rgb(0, 0, 0);">₹</h6>
                            
                            <input id="quantity" type="number" class="form-control bfh-number" name="quantity" value="30" size="4" min="30">

                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
      
                  <div class="d-flex justify-content-between align-items-center pb-1">
                    <button type="button" class="btn btn-sm" style = "color:beige"><a href="#!" class= "text-muted" style="text-align: left;color: rgb(0, 0, 0);">Go back</a></button>
                    <input type="submit" class="btn btn-dark btn-sm" value="Pay Amount">
                
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        </form>
      </section>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>