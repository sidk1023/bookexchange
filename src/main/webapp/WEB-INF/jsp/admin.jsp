
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
            <a class="nav-link active" href="admin">Admin Controls</a>
          </li>
        </ul>
      </div>
    </div>
</nav>   
<section class="container-fluid" style="background-color:#e7e0da">
    <h1 class="py-5" style="text-align:center; color: #703f37;">View All Users</h1>
    <div class="container">
        <div class="row row-cols-3">
            <c:forEach items="${userList}" var="user">
                <div class="card d-inline-flex mt-4 ms-4" >
                    <div class="card-body">
                      <h5 class="card-title">${user.name}</h5>
                      <p class="card-text">
                        <div>Name: ${user.name}</div>
                        <div>Email: ${user.email}</div>
                        <div>Phone: ${user.phoneNumber}</div>
                        <div>Location: ${user.address}</div>
                        
                    </p>
                    <div class="row row-cols-4">
                        <form action="edituseradmin" method="post">
                          <input type="hidden" value=${user.id} name = "userId">
                           <input type="submit" value="Edit">
                           </form>
                           <form action="deleteuser" method="post">
                            <input type="hidden" value=${user.id} name = "userId">
                               <input type="submit" value="Delete">
                        </form>
                        <form action="userreport" method="post">
                            <input type="hidden" value=${user.id} name = "userId">
                               <input type="submit" value="Generate Report">
                        </form>
                      </div>
               
                </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<section class="container-fluid" style="background-color:#e7e0da">
    <h1 class="py-5" style="text-align:center; color: #703f37;">View All Books</h1>
    <div class="container">
        <div class="row row-cols-3">
            <c:forEach items="${bookList}" var="book">
                <div class="card d-inline-flex" style="width: 20rem;">
                    <div class="card-body">
                      <h5 class="card-title">${book.title}</h5>
                      <p class="card-text">
                          <div>Author: ${book.author}</div>
                          <div>Lender: ${book.lender.name}</div>
                          <div>Status: ${book.status}</div>
                          <div>Location: ${book.lender.address}</div>
                      </p>
                      
                      <div class="row row-cols-2">
                        <form action="editbookadmin" method="post">
                          <input type="hidden" value=${book.id} name = "bookId">
                           <input type="submit" value="Edit">
                           </form>
                           <form action="deletebookadmin" method="post">
                            <input type="hidden" value=${book.id} name = "bookId">
                               <input type="submit" value="Delete">
                        </form>
                </div>
               
                    </div>
                  </div>
                    </c:forEach>
        </div>
    </div>
</section>

<section class="container-fluid" style="background-color:#e7e0da">
    <h1 class="py-5" style="text-align:center; color: #703f37;">View All Borrow Requests</h1>
    <div class="container">
        <div class="row row-cols-3">
            <c:forEach items="${requests}" var="request">
                <div class="card d-inline-flex mt-4 ms-4" style="width: 20rem;">
                    <div class="card-body">
                      <h5 class="card-title">${request.book.title}</h5>
                      <p class="card-text">
                        <div>Author: ${request.book.author}</div>
                        <div>Lender: ${request.lender.email}</div>
                        <div>Borrower: ${request.borrower.name}</div>
                        <div>Status: ${request.status}</div>
                    </p>
              
               
                </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>