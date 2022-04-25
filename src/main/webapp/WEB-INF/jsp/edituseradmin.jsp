
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
<body style = "background-color: #eee">
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
    <h1 style="text-align: center;color: black;" >Edit User Profile</h1>
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col col-lg-6 mb-4 mb-lg-0">
    <form action="submitedituseradmin" method = "post">
        <!-- 2 column grid layout with text inputs for the first and last names -->
        <div class="row mb-4">
          <div class="col">
            <div class="form-outline">
                <label class="form-label" for="form6Example1">Name</label>
              <input type="text" id="form6Example1" class="form-control" value=${user.name} name="name" />
            </div>
          </div>
    
        </div>
      
        <!-- Email input -->
        <div class="form-outline mb-4">
            <label class="form-label" for="form6Example5">Email</label>
          <input type="email" id="form6Example5" class="form-control" value=${user.email} name = "email"/>
          
        </div>
      
        <!-- Number input -->
        <div class="form-outline mb-4">
            <label class="form-label" for="form6Example6">Phone</label>
          <input type="number" id="form6Example6" class="form-control"  value=${user.phoneNumber} name = "phoneNumber" />
         
        </div>
      
        <!-- Password input -->
        <div class="form-outline">
            <label class="form-label" for="formControlDefault">Change Password </label>
            <input name = "password"  type="text" id="formControlDefault" class="form-control" />
          </div>
      
          <input type="hidden" value=${user.id} name = "userId">
        <!-- Submit button -->
        <button type="submit" class="btn btn-primary btn-block mb-4"> Confirm Edits </button>
      </form>
     
         </div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>