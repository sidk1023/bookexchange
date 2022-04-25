<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>My Requests</title>
    <style>
        .nav-item{
            margin: 10px 18px;
        }
        .navbar-brand img{
            height: 42px;
            width: 42px;
        }
    </style>
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
                <a class="nav-link" href="myprofile">My Profile</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="mybooks">My Books</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="myrequests">My Requests</a>
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
    <section class="container-fluid" style="background-color:#e7e0da">
        <h1 class="py-5" style="text-align:center; color: #703f37;">My Borrow Requests</h1>
        <div class="container">
            <div class="row row-cols-3">
                <c:forEach items="${requestListBorrow}" var="request">
                    <div class="card d-inline-flex mt-4 ms-4" style="width: 20rem;">
                        <div class="card-body">
                          <h5 class="card-title">${request.book.title}</h5>
                          <p class="card-text">
                              <div>Lender: ${request.book.lender.name}</div>
                              <div>Status: ${request.status}</div>
                          </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
    
    <section class="container-fluid" style="background-color:#e7e0da">
        <h1 class="py-5" style="text-align:center; color: #703f37;">My Lend Requests</h1>
        <div class="container">
            <div class="row row-cols-3">
                <c:forEach items="${requestListLend}" var="request">
                    <div class="card d-inline-flex mt-4 ms-4" style="width: 20rem;">
                        <div class="card-body">
                          <h5 class="card-title">${request.book.title}</h5>
                          <p class="card-text">
                              <div>Borrower: ${request.borrower.name}</div>
                              <div>Status: ${request.status}</div>
                          </p>
                          <div class="row row-cols-2">
                          <form action="acceptrequest" method="post">
                            <input type="hidden" value="${request.id}" name = "request">
                            <input type="hidden" value="${user.id}" name = "lender">
                             <input type="submit" value="Accept">
                             </form>
                             <form action="declinerequest" method="post">
                                <input type="hidden" value="${request.id}" name = "request">
                                <input type="hidden" value="${user.id}" name = "lender">
                                 <input type="submit" value="Decline">
                          </form>
                        </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>

    <section class="container-fluid" style="background-color:#e7e0da">
        <h1 class="py-5" style="text-align:center; color: #703f37;">Borrowed Books</h1>
        <div class="container">
            <div class="row row-cols-3">
                <c:forEach items="${requestListBorrowAccepted}" var="request">
                    <div class="card d-inline-flex mt-4 ms-4" style="width: 20rem;">
                        <div class="card-body">
                          <h5 class="card-title">${request.book.title}</h5>
                          <p class="card-text">
                              <div>Lender: ${request.lender.name}</div>
                              <div>Status: ${request.status}</div>
                              <div>Issue Date: ${request.issueDate}</div>
                              <div>Return Date: ${request.returnDate}</div>
                          </p>
                          <div class="row row-cols-2">
                            <form action="extendrequest" method="post">
                              <input type="hidden" value="${request.id}" name = "request">
                              <input type="hidden" value="${user.id}" name = "borrower">
                               <input type="submit" value="Extend">
                               </form>
                               <form action="returnrequest" method="post">
                                  <input type="hidden" value="${request.id}" name = "request">
                                  <input type="hidden" value="${user.id}" name = "borrower">
                                   <input type="submit" value="Return">
                            </form>
                          </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
    <section class="container-fluid" style="background-color:#e7e0da">
        <h1 class="py-5" style="text-align:center; color: #703f37;">Lended Books</h1>
        <div class="container">
            <div class="row row-cols-3">
                <c:forEach items="${requestListLendAccepted}" var="request">
                    <div class="card d-inline-flex mt-4 ms-4" style="width: 20rem;">
                        <div class="card-body">
                          <h5 class="card-title">${request.book.title}</h5>
                          <p class="card-text">
                              <div>Borrower Name: ${request.borrower.name}</div>
                              <div>Status: ${request.status}</div>
                          </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
    <section class="container-fluid" style="background-color:#e7e0da">
        <h1 class="py-5" style="text-align:center; color: #703f37;">Extension Requests</h1>
        <div class="container">
            <div class="row row-cols-3">
                <c:forEach items="${extensionRequestsLender}" var="request">
                    <div class="card d-inline-flex mt-4 ms-4" style="width: 20rem;">
                        <div class="card-body">
                          <h5 class="card-title">${request.book.title}</h5>
                          <p class="card-text">
                              <div>Borrower Name: ${request.borrower.name}</div>
                              <div>Status: ${request.status}</div>
                          </p>
                          <div class="row row-cols-2">
                          <form action="acceptextension" method="post">
                            <input type="hidden" value="${request.id}" name = "request">
                            <input type="hidden" value="${user.id}" name = "lender">
                             <input type="submit" value="Accept">
                             </form>
                             <form action="rejectextension" method="post">
                                <input type="hidden" value="${request.id}" name = "request">
                                <input type="hidden" value="${user.id}" name = "lender">
                                 <input type="submit" value="Reject">
                          </form>
                        </div>
                        </div>
                    </div>
                </c:forEach>
                <c:forEach items="${extensionRequestsBorrower}" var="request">
                    <div class="card d-inline-flex mt-4 ms-4" style="width: 20rem;">
                        <div class="card-body">
                          <h5 class="card-title">${request.book.title}</h5>
                          <p class="card-text">
                              <div>Lender Name: ${request.lender.name}</div>
                              <div>Status: ${request.status}</div>
                          </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>

    
    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    
  </body>
</html>