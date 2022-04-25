<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <title>
            Home Page
        </title>
    </head>
    
    <body style="background-color:#e7e0da">
      
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
                <a class="nav-link  active" href="home">Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="myprofile">My Profile</a>
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
          <div class="container">
        <h1 style="text-align:center; color: #703f37;">Book List</h1>
        <div>A deposit of ₹30 is charged which will be returned to your wallet if book is returned before the return date<div>
        
        <c:forEach items="${bookList}" var="book">
            <div class="card d-inline-flex" style="width: 18rem;">
                <div class="card-body">
                  <h5 class="card-title">${book.title}</h5>
                  <p class="card-text">
                      <div>Author: ${book.author}</div>
                      <div>Lender: ${book.lender.name}</div>
                      <div>Status: ${book.status}</div>
                      <div>Location: ${book.lender.address}</div>
                  </p>
                  
                  <form action="requestbook" method="post">
                    <input type="hidden" value="${book.id}" name = "book">
                    <input type="hidden" value="${user.id}" name = "borrower">
                    <input type="hidden" value="${book.lender.id}" name = "lender">
                     <input type="submit" value="Request">
                  </form>
           
                </div>
              </div>
                </c:forEach>
            
        </div>
      
      </div>
    
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
