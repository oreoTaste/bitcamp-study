<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<div class='container'>

<h1>멤버 추가</h1>

      <form action='add' method='post' enctype='multipart/form-data'>
      <table>
      <tr>
      <td>성함</td>
      <td><input name='name' type='text' style='width:300px'></td>
      </tr>
      <tr>
      <td>이메일</td>
      <td><input name='email' type="email" style='width:300px'></td>
      </tr>
      <tr>
      <td>비밀번호</td>
      <td><input name='password' type="password" style='width:300px'></td>
      </tr>
      <tr>
      <td>사진</td>
      <td><input name='photoFile' type='file' style='width:300px'></td>
      </tr>
      <tr>
      <td>전화번호</td>
      <td><input name='tel' type='text' style='width:300px'></td>
      </tr>
      </table>
      <button>등록</button>
      </form>


</div>
