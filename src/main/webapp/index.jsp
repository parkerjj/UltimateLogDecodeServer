<!DOCTYPE html>
<!-- [START_EXCLUDE] -->
<%--
  ~ Copyright 2017 Google Inc.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License"); you
  ~ may not use this file except in compliance with the License. You may
  ~ obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
  ~ implied. See the License for the specific language governing
  ~ permissions and limitations under the License.
  --%>
<!-- [END_EXCLUDE] -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href='//fonts.googleapis.com/css?family=Marmelad' rel='stylesheet' type='text/css'>
    <title>Hello App Engine Standard Java 8</title>
</head>
<body>
<h1>Hello App Engine -- Java 8!</h1>


<br/>
<form action="./upload" class="form" method="POST" enctype="multipart/form-data">
    Select file to upload:
    <br>
    <input multiple id="file" name="attachments" type="file"/>
    <br/>
    <input name="seed" type="text" placeholder="Seed…" value="" autocomplete="off" maxlength="240" class="">
    <br>
    <input type="submit" value="Upload xLog">
</form>

</body>
</html>
