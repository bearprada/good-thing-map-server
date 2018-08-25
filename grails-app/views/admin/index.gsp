<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="layout" content="main" />
  <title>Admin</title>
</head>
<body>
  Import Good Things
  <g:form controller="admin" method="post" action="upload" enctype="multipart/form-data">
    CSV File : <input type="file" name="file" />
    <input type="submit" />
  </g:form>
  Insert A New Photo
  <g:form controller="admin" method="post" action="imageUpload" enctype="multipart/form-data">
  	Good Thing ID : <g:textField name="id"/>
    Image : <input type="file" name="file_image" />
    <input type="submit" />
  </g:form>
  Insert A New Cover Image
  <g:form controller="admin" method="post" action="coverImageUpload" enctype="multipart/form-data">
    Good Thing ID : <g:textField name="id"/>
    Image : <input type="file" name="file_image" />
    <input type="submit" />
  </g:form>
</body>
</html>
