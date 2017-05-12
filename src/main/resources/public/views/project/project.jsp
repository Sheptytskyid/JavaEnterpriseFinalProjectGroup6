<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
<%--    pageEncoding="ISO-8859-1"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html ng-app="projectManagerApp">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>AngularJS Task Manager</title>
    <script data-require="angular.js@*" data-semver="1.2.13" src="http://code.angularjs.org/1.2.13/angular.js"></script>
    <script type="text/javascript" src="/static/js/test.js"></script>
</head>
<body>
<div ng-controller="projectManagerController">
    <h1>Hello Projects</h1>
     
    <div id="task-panel" class="fadein fadeout showpanel panel" ng-show="toggle">
          
        <div class="panel-heading">
               <i class="panel-title-icon fa fa-tasks"></i>
               <span class="panel-title">Projects</span>
               
            <div class="panel-heading-controls">
                    
                <button ng-click="toggle = !toggle">Add Project</button>
                    
                <button confirmed-click="archiveTasks()" ng-confirm-click="Would you like to archive completed tasks?">
                    Clear projects
                </button>
                   
            </div>
              
        </div>
          
        <div>
               
            <div ng-repeat="project in projects">
                <%--    <span>{{project.name}}</span>--%>
                <%--<span>{{project.desc.name}}</span>--%>
                <%--<span>{{project.desc.goal}}</span>--%>
                <%--<span>{{project.category.name}}</span>--%>
                <%--<span>{{project.desc.cost}}</span>--%>
                <%--<div>--%>
                <%--     <input id="{{project.projectID}}" type="checkbox" value="{{task.taskId}}"--%>
                <%--ng-checked="selection.indexOf(project.projectID) > -1"--%>
                <%--ng-click="toggleSelection(project.projectID)"/>--%>
                <%--       <label for="{{project.projectID}}"></label> --%>
                <%--</div>--%>
                <%--    --%>
                <%--<div ng-if="task.taskStatus=='COMPLETED'">   --%>
                <%--     <a href="#" class="checkedClass">--%>
                <%--      {{task.taskName}}--%>
                <%--     <span class="action-status">{{task.taskStatus}}</span>--%>
                <%--     </a>--%>
                <%--    --%>
                <%--</div>--%>
                    
                <%--<div ng-if="task.taskStatus=='ACTIVE'">   --%>
                <%--     <a href="#" class="uncheckedClass">--%>
                <%--      {{task.taskName}}--%>
                <%--      <span class="action-status">{{task.taskStatus}}</span>--%>
                <%--     </a>--%>
                <%--</div>--%>
            </div>
              
        </div>
         
    </div>
     
    <div id="add-project-panel" ng-hide="toggle">
          
        <div>
               <span>Add Project</span>
               
            <div>
                    
                <button ng-click="toggle = !toggle">Show All Projects</button>
                   
            </div>
              
        </div>
          
        <div>
               
            <div>
                    
                <table>
                         
                    <tr>
                              
                        <td>Project Name:</td>
                              
                        <td><input type="text" ng-model="projectName"/></td>
                             
                    </tr>
                         
                    <tr>
                             
                        <td>Task Description:</td>
                              
                        <td><input type="text" ng-model="projectDesc"/></td>
                         
                    </tr>
                         
                    <tr>
                              
                        <td>Project Category</td>
                              
                        <td>
                                   <select ng-model="projectCategory"
                                           ng-options="category as category for category in categories">
                                    
                            <option value="">-- Select --</option>
                                   </select>
                              
                        </td>
                             
                    </tr>
                         
                    <tr>
                              
                        <td>
                            <button ng-click="addProgect()" class="btn-panel-big">Add new Project</button>
                        </td>
                             
                    </tr>
                        
                </table>
                       
            </div>
              
        </div>
    </div>
</div>
</body>
</html>