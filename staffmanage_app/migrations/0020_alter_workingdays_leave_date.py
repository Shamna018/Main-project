# Generated by Django 3.2.18 on 2023-04-28 08:33

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('staffmanage_app', '0019_rename_wokingdays_workingdays'),
    ]

    operations = [
        migrations.AlterField(
            model_name='workingdays',
            name='leave_date',
            field=models.DateField(default='2023-04-01', max_length=50),
        ),
    ]
