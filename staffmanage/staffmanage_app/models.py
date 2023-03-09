from django.db import models

# Create your models here.
class login(models.Model):
    username = models.CharField(max_length=50)
    password = models.CharField(max_length=50)
    usertype = models.CharField(max_length=50)

class department(models.Model):
    department_name = models.CharField(max_length=50)

class designation(models.Model):
    DEPARTMENT = models.ForeignKey(department, on_delete=models.CASCADE)
    designation_name = models.CharField(max_length=50)

class basicpay(models.Model):
    DESIGNATION = models.ForeignKey(designation, on_delete=models.CASCADE)
    basicpay = models.CharField(max_length=50)

class staff(models.Model):
    LOGIN = models.ForeignKey(login,on_delete=models.CASCADE)
    name = models.CharField(max_length=50)
    gender = models.CharField(max_length=50)
    dob = models.CharField(max_length=50)
    mobile = models.CharField(max_length=50)
    email = models.CharField(max_length=50)
    address = models.CharField(max_length=50)
    qualification = models.CharField(max_length=50)
    experiance = models.CharField(max_length=50)
    father = models.CharField(max_length=50)
    mother = models.CharField(max_length=50)
    joindate = models.CharField(max_length=50)
    acctno = models.CharField(max_length=50)
    ifsc = models.CharField(max_length=50)
    recipient_name = models.CharField(max_length=50)
    photo = models.FileField(max_length=50)
    status = models.CharField(max_length=50)
    DESIGNATION = models.ForeignKey(designation, on_delete=models.CASCADE)

class assignwork(models.Model):
    STAFF = models.ForeignKey(staff, on_delete=models.CASCADE)
    date = models.CharField(max_length=50)
    work = models.CharField(max_length=50,default=0)

class notification(models.Model):
    DEPARTMENT = models.ForeignKey(department, on_delete=models.CASCADE)
    date = models.CharField(max_length=50)
    title = models.CharField(max_length=50)
    content = models.CharField(max_length=50)

class allnotification(models.Model):
    date = models.CharField(max_length=50)
    title = models.CharField(max_length=50)
    content= models.CharField(max_length=50)

class deduction(models.Model):
    deduction_type = models.CharField(max_length=50)
    salary_limit = models.CharField(max_length=50)
    percentage = models.CharField(max_length=50)




