# Generated by Django 3.2.18 on 2023-03-31 05:46

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('staffmanage_app', '0011_attendance'),
    ]

    operations = [
        migrations.RenameField(
            model_name='attendance',
            old_name='status',
            new_name='entry_status',
        ),
        migrations.AddField(
            model_name='attendance',
            name='exit_status',
            field=models.CharField(default='pending', max_length=50),
            preserve_default=False,
        ),
    ]
