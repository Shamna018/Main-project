from django.urls import path
from staffmanage_app import views


urlpatterns = [

    path('demo/', views.hello),
    path('home/',views.home),
    path('login/', views.login_fun),
    path('login_post/', views.login_post),

    path('registration/',views.registration),
    path('basicsallaryget/',views.basicsallaryget),

    path('registration_post/',views.registration_post),
    path('viewstaff/',views.viewstaff),
    path('updatestaff/<str:did>',views.update_staff),
    path('updatestaff_post/',views.update_staff_post),
    path('viewmorestaff/<str:did>',views.staffviewmore),

    path('department/',views.department_fun),
    path('department_post/',views.department_post),
    path('admincheckdept/',views.admincheckdept),
    path('view_department/',views.view_dept),
    path('update_department/<str:did>',views.update_dept),
    path('update_department_post/',views.update_dept_post),
    path('delete_department/<str:id>',views.delete_dept),


    path('designation/',views.designation_fun),
    path('designation_post/',views.designation_post),
    path('admincheckdesignation/',views.admincheckdesignation),
    path('view_designation/',views.view_designation),
    path('update_designation/<str:did>',views.update_designation),
    path('update_designation_post/',views.update_designation_post),
    path('delete_designation/<str:id>',views.delete_designation),

    path('basicpay/',views.basicpay_fun),
    path('checkbasicpay/',views.checkbasicpay),
    path('basicpay_post/',views.basicpay_post),
    path('view_basicpay/',views.view_basicpay),
    path('update_basicpay/<did>',views.update_basicpay),
    path('update_basicpay_post/',views.update_basicpay_post),
    path('getdesignationdep/',views.getdesignationdep),




    path('deduction/',views.deduction_fun),
    path('deduction_post/',views.deduction_post),
    path('admincheckdeduction/',views.admincheckdeduction),
    path('view_deduction/',views.view_deduction),
    path('update_deduction/<str:did>',views.update_deduction),
    path('update_deduction_post/',views.update_deduction_post),

    path('notification/',views.notification_fun),
    path('notification_post/',views.notification_post),

    path('assignwork/',views.assignwork_fun),
    path('assignwork_post/',views.assignwork_post),
    path('getdesignationstaff/',views.getdesignationstaff),

]