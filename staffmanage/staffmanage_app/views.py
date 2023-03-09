from django.core.files.storage import FileSystemStorage
from django.shortcuts import render, redirect
from django.http import HttpResponse,JsonResponse
from staffmanage_app.models import *
# Create your views here.



def hello(request):
    return render(request,'landing_index.html')

def login_fun(request):
    return render(request,'loginindex.html')

def home(request):
    return render(request,'admin_index.html')

def login_post(request):
    username = request.POST['textfield']
    password = request.POST['textfield2']
    if login.objects.filter(username=username, password=password).exists():
        d = login.objects.get(username=username, password=password)
        checkuser=d.username==username
        checkpass=d.password==password
        request.session["lid"] = d.id
        if checkuser and checkpass and d.usertype == "admin":
            return render(request, "admin_index.html")
        else:
            return HttpResponse('''<script>alert('invalid');window.location='/myapp/login/'</script>''')
    else:
        return HttpResponse('''<script>alert('invalid');window.location='/myapp/login/'</script>''')

def registration(request):
    res2 = designation.objects.all()
    res3 = department.objects.all()

    return render(request,'registration.html',{'data2':res3,'data3':res2})


def basicsallaryget(request):
    l=[]
    des=request.POST['desid']
    bob=basicpay.objects.get(DESIGNATION=des)
    print(bob,"---------------------------")
    print(bob.basicpay,"=======================")
    return JsonResponse({'data':bob.basicpay})








def registration_post(request):
    name = request.POST['textfield']
    gender = request.POST['radio']
    dob = request.POST['textfield12']
    mobile = request.POST['textfield2']
    email = request.POST['textfield3']
    address = request.POST['textarea']
    qualification = request.POST['textfield4']
    experiance = request.POST['textfield5']
    father = request.POST['textfield6']
    mother = request.POST['textfield7']
    department = request.POST['select']
    designation = request.POST['select2']
    basicpay = request.POST['textfield11']
    joindate = request.POST['textfield13']
    acctno = request.POST['textfield8']
    ifsc = request.POST['textfield9']
    recipientname = request.POST['textfield10']
    photo = request.FILES['fileField']


    lobj=login()
    lobj.username=email
    lobj.password=mobile
    lobj.usertype='staff'
    lobj.save()

    from datetime import datetime
    date = datetime.now().strftime("%Y%m%d%H%M%S") + ".jpg"
    fs = FileSystemStorage()
    fn = fs.save(date, photo)
    path = fs.url(date)



    obj = staff()
    obj.name=name
    obj.gender=gender
    obj.dob=dob
    obj.mobile=mobile
    obj.email=email
    obj.address=address
    obj.qualification=qualification
    obj.experiance=experiance
    obj.father=father
    obj.mother=mother
    obj.joindate=joindate
    obj.acctno=acctno
    obj.ifsc=ifsc
    obj.recipient_name=recipientname
    obj.photo=path
    obj.DESIGNATION_id=designation
    obj.LOGIN=lobj
    obj.status='joined'
    obj.save()
    return HttpResponse('ok')


def viewstaff(request):
    res =staff.objects.all()
    return render(request,'viewstaff.html', {'data': res})

def staffviewmore(request,did):
    res = staff.objects.get(id=did)
    res2=basicpay.objects.filter(DESIGNATION=res.DESIGNATION)
    if res2 is None:
        return render(request, 'staffview.html', {'data': res, 'data2': "None"})
    else:
        res2 = basicpay.objects.get(DESIGNATION=res.DESIGNATION)
        return render(request, 'staffview.html', {'data': res,'data2':res2})


def update_staff(request,did):
    res = staff.objects.get(id=did)
    res4 = designation.objects.all()
    res3 = department.objects.all()
    res2=basicpay.objects.get(DESIGNATION=res.DESIGNATION)
    request.session['sid']=did

    return render(request,'updatestaff.html',{'data':res,'data2':res2,'data3':res3,'data4':res4})

def update_staff_post(request):
    name = request.POST['textfield']
    gender = request.POST['radio']
    dob = request.POST['textfield12']
    mobile = request.POST['textfield2']
    email = request.POST['textfield3']
    address = request.POST['textarea']
    qualification = request.POST['textfield4']
    experiance = request.POST['textfield5']
    father = request.POST['textfield6']
    mother = request.POST['textfield7']
    department = request.POST['select']
    designation = request.POST['select2']
    # basicpay = request.POST['textfield11']
    joindate = request.POST['textfield13']
    acctno = request.POST['textfield8']
    ifsc = request.POST['textfield9']
    recipientname = request.POST['textfield10']
    sobj = staff.objects.get(id=request.session['sid'])

    if 'fileField' in request.FILES:
        photo = request.FILES['fileField']
        if photo.name!="":
            from datetime import datetime
            date = datetime.now().strftime("%Y%m%d%H%M%S") + ".jpg"
            fs = FileSystemStorage()
            fn = fs.save(date, photo)
            path = fs.url(date)

            sobj.name=name
            sobj.gender=gender
            sobj.dob=dob
            sobj.mobile = mobile
            sobj.email = email
            sobj.address = address
            sobj.qualification = qualification
            sobj.experiance = experiance
            sobj.father = father
            sobj.mother = mother
            sobj.joindate = joindate
            sobj.acctno = acctno
            sobj.ifsc = ifsc
            sobj.recipient_name = recipientname
            sobj.DESIGNATION_id=designation
            sobj.photo=path
            sobj.save()
            return HttpResponse("ok")
        else:
            sobj.name = name
            sobj.gender = gender
            sobj.dob = dob
            sobj.mobile = mobile
            sobj.email = email
            sobj.address = address
            sobj.qualification = qualification
            sobj.experiance = experiance
            sobj.father = father
            sobj.mother = mother
            sobj.joindate = joindate
            sobj.acctno = acctno
            sobj.ifsc = ifsc
            sobj.recipient_name = recipientname
            sobj.DESIGNATION_id = designation
            sobj.save()
            return HttpResponse("ok")
    else:
        sobj.name = name
        sobj.gender = gender
        sobj.dob = dob
        sobj.mobile = mobile
        sobj.email = email
        sobj.address = address
        sobj.qualification = qualification
        sobj.experiance = experiance
        sobj.father = father
        sobj.mother = mother
        sobj.joindate = joindate
        sobj.acctno = acctno
        sobj.ifsc = ifsc
        sobj.recipient_name = recipientname
        sobj.DESIGNATION_id = designation
        sobj.save()
        return HttpResponse("Update successfully")
        # return HttpResponse(
        #     "<script>alert('Update successfully');window.location='/myapp/updatestaff/#about'</script>")


def department_fun(request):
    return render(request,'department.html')

def department_post(request):
    dept = request.POST['textfield']
    obj = department()
    obj.department_name = dept
    obj.save()
    return redirect("/myapp/view_department/#about")

def admincheckdept(request):
    c=request.GET["c"]
    print(c)
    h=department.objects.filter(department_name=c)
    if h.exists():
        return JsonResponse({'status':'no'})
    else:
        return JsonResponse({'status':'ok'})


def view_dept(request):
    res = department.objects.all
    return render(request,'viewdepartment.html',{'data':res})

def update_dept(request,did):
    res =department.objects.get(id=did)
    return render(request,'updatedept.html',{'data':res})

def update_dept_post(request):
    did = request.POST['h1']
    dept=request.POST['textfield']
    res = department.objects.filter(pk=did).update(department_name=dept)
    return redirect("/myapp/view_department/#about")

def delete_dept(request,id):
    res = department.objects.get(pk=id).delete()
    return redirect("/myapp/view_department/#about")


def designation_fun(request):
    res = department.objects.all()
    print(res)
    return render(request,'designation.html',{'data':res})

def designation_post(request):
    departmentname = request.POST['select']
    desig_name = request.POST['textfield']
    obj =designation()
    obj.designation_name = desig_name
    res = department.objects.get(id=departmentname)
    obj.DEPARTMENT=res
    obj.save()
    return redirect("/myapp/view_designation/#about")

def admincheckdesignation(request):
    c=request.GET["c"]
    d=request.GET["d"]
    print(c,d)
    h=designation.objects.filter(DEPARTMENT=c,designation_name=d)
    if h.exists():
        return JsonResponse({'status':'no'})
    else:
        return JsonResponse({'status':'ok'})

def view_designation(request):
    res = designation.objects.all()
    return render(request,'viewdesignation.html',{'data':res})

def update_designation(request,did):
    res = designation.objects.get(id=did)
    res2=department.objects.all()
    print(res2)
    return render(request,'updatedesignation.html',{'data':res,'data2':res2})

def update_designation_post(request):
    did = request.POST['h1']
    dept = request.POST['select']
    print(dept)
    designatio=request.POST['textfield']
    print(designation)
    res = designation.objects.filter(pk=did).update(designation_name=designatio,DEPARTMENT=dept)
    return redirect("/myapp/view_designation/#about")

def delete_designation(request,id):
    res = designation.objects.get(pk=id).delete()
    return redirect("/myapp/view_designation/#about")


def basicpay_fun(request):
    res=department.objects.all()
    res2 = designation.objects.all()
    return render(request,'basicpay.html',{'data':res,'data2':res2})



def checkbasicpay(request):
    c=request.GET["c"]
    print(c)
    h=basicpay.objects.filter(DESIGNATION=c)
    if h.exists():
        return JsonResponse({'status':'no'})
    else:
        return JsonResponse({'status':'ok'})



def basicpay_post(request):
    desigantions = request.POST['select']
    basic_pay = request.POST['textfield']
    obj = basicpay()
    obj.basicpay=basic_pay
    obj.DESIGNATION_id = desigantions

    obj.save()
    return redirect("/myapp/view_basicpay/#about")

def view_basicpay(request):
    res = basicpay.objects.all()
    return render(request,'viewbasicpay.html',{'data':res})

def update_basicpay(request,did):
    request.session['bid']=did
    res = basicpay.objects.get(id=did)
    res2=designation.objects.get(id=res.DESIGNATION_id)
    return render(request,'updatebasicpay.html',{'data':res,'data2':res2})



def getdesignationdep(request):
    lst=[]
    did=request.POST['did']
    res=designation.objects.filter(DEPARTMENT_id=did)
    print(res)
    for i in res:
        lst.append({'designation_name':i.designation_name,'id':i.id})

    return JsonResponse({'data':lst})



def getdesignationstaff(request):
    lst=[]
    did=request.POST['did']
    res=staff.objects.filter(DESIGNATION=did)
    print(res)
    for i in res:
        lst.append({'name':i.name,'id':i.id})
    return JsonResponse({'data':lst})





def update_basicpay_post(request):
    bpay = request.POST['textfield']
    res = basicpay.objects.filter(pk=request.session['bid']).update(basicpay=bpay)
    return redirect("/myapp/view_basicpay/#about")


def deduction_fun(request):
    return render(request,'deduction.html')

def deduction_post(request):
    deductiontype = request.POST['textfield']
    salarylimit = request.POST['textfield2']
    percentage = request.POST['textfield3']
    obj = deduction()
    obj.deduction_type=deductiontype
    obj.salary_limit=salarylimit
    obj.percentage =percentage
    obj.save()
    return redirect("/myapp/view_deduction/#about")

def admincheckdeduction(request):
    c=request.GET["c"]
    print(c)
    h=deduction.objects.filter(deduction_type=c)
    if h.exists():
        return JsonResponse({'status':'no'})
    else:
        return JsonResponse({'status':'ok'})

def view_deduction(request):
    res = deduction.objects.all
    return render(request,'viewdeduction.html',{'data':res})

def update_deduction(request,did):
    res = deduction.objects.get(id=did)
    return render(request,'updatededuction.html',{'data':res})

def update_deduction_post(request):
    did = request.POST['h1']
    dept_type = request.POST['textfield']
    sal_limit = request.POST['textfield2']
    percnt = request.POST['textfield3']
    res = deduction.objects.filter(pk=did).update(deduction_type=dept_type,salary_limit=sal_limit,percentage=percnt)

    return redirect("/myapp/view_deduction/#about")

def notification_fun(request):
    res = department.objects.all()
    return render(request,'notification.html',{'data':res})

def notification_post(request):

    from datetime import datetime
    departmentname = request.POST['select']
    title = request.POST['textfield2']
    content = request.POST['textarea']
    if departmentname=="All":
        obj=allnotification()
        obj.date = datetime.now()
        obj.title = title
        obj.content = content
        obj.save()
        return HttpResponse("<script>alert('Notification send successfully');window.location='/myapp/notification/#about'</script>")
    else:
        obj = notification()
        obj.date=datetime.now()
        obj.title=title
        obj.content=content
        res = department.objects.get(id=departmentname)
        obj.DEPARTMENT = res
        obj.save()
        return HttpResponse("<script>alert('Notification send successfully');window.location='/myapp/notification/#about'</script>")









def assignwork_fun(request):
    res = department.objects.all()
    res1 = staff.objects.all()
    res2 = designation.objects.all()

    return render(request,'assignwork.html',{'data':res,'data1':res1,'data2':res2})

def assignwork_post(request):
    staffname = request.POST['select1']
    date = request.POST['textfield']
    designationname = request.POST['select2']
    work = request.POST['textfield1']

    obj = assignwork()
    obj.date=date
    obj.work = work
    obj.STAFF_id=staffname
    obj.DESIGNATION_id=designationname
    obj.save()
    # return HttpResponse('ok')
    return HttpResponse(
            "<script>alert('Work assigned successfully');window.location='/myapp/assignwork/#about'</script>")
