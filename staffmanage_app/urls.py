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
    path('viewstaffattendance/<str:did>',views.viewstaffattendance),
    path('search_by_month/',views.search_by_month),
    path('delete_staff/<str:id>',views.delete_staff),

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

    path('allowance/',views.allowance_fun),
    path('allowance_post/',views.allowance_post),
    path('view_allowance/',views.view_allowance),
    path('delete_allowance/<str:id>',views.delete_allowance),
    path('update_allowance/<did>',views.update_allowance),
    path('update_allowance_post/',views.update_allowance_post),
    path('admincheckallowance/',views.admincheckallowance),
    path('view_allowancepost/',views.view_allowancepost),
    path('getdesignationdepart/',views.getdesignationdepart),

    path('attendance/',views.attendance_post),
    path('save_attedance/',views.save_attendance),
    path('view_attendance/',views.view_attendance),
    path('view_attendance_search/',views.view_attendance_search),

    path('view_salary/<id>',views.view_salary),
    path('salary_report/',views.salary_report),
    path('view_salaryreport_search/',views.view_salaryreport_search),
    path('view_salary_post/',views.view_salary_post),

    path('workingday/',views.workingday),
    path('workingday_post/',views.workingday_post),
    path('view_workday/',views.view_workday),
    path('delete_workday/<str:id>',views.delete_workday),

    path('view_staff_leave_request/',views.view_staff_leave_request),
    path('view_leave_request_form/<did>',views.view_leave_request_form),
    path('accept_leave/<did>',views.accept_leave),
    path('reject_leave/<did>',views.reject_leave),

    path('view_staff_resignation/',views.view_staff_resignation),
    path('view_resignation_request_form/<did>/<sid>',views.view_resignation_request_form),
    path('accept_resignation/<did>',views.accept_resignation),
    path('reject_resignation/<did>',views.reject_resignation),

    path('staff_login_post/', views.staff_login_post),
    path('staff_profile/',views.staff_profile),
    path('staff_view_notification/',views.staff_view_notification),
    path('staff_view_assignwork/',views.staff_view_assignwork),
    path('staff_view_attendance/',views.staff_view_attendance),
    path('datesearch/',views.datesearch),
    path('leave_request_form/',views.leave_request_form),
    path('staff_view_leave_request/',views.staff_view_leave_request),
    path('send_resign/',views.send_resign),
    path('staff_view_resignation/',views.staff_view_resignation),
    path('staff_view_salary/',views.staff_view_salary),
    path('staff_view_salary_post/',views.staff_view_salary_post),
    path('staff_monthsearch/',views.staff_monthsearch),
    path('payslip/',views.payslip),
]