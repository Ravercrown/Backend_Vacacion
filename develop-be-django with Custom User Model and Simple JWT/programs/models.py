from django.db import models
from django.conf import settings
from django.core.validators import MaxValueValidator, MinValueValidator

class TypeProject(models.Model):
    type = models.CharField(max_length=64)
    description = models.CharField(max_length=255)

    def __str__(self):
        return f'{self.type}'

class Ubication(models.Model):
    title = models.CharField(max_length=255)

    def __str__(self):
        return f'{self.title}'

class Project(models.Model):
    title = models.CharField(max_length=255)
    description = models.TextField()
    value_total = models.FloatField()
    units = models.BigIntegerField()
    expected_utility = models.DecimalField(max_digits=4, decimal_places=2)
    duration = models.IntegerField(default=1, validators=[
        MaxValueValidator(24),
        MinValueValidator(1)
    ])

    def _value_unit(self):
        value = self.value_total/self.units
        return value

    value_unit = property(_value_unit)
    policy = models.BooleanField(default=True)

    ubication = models.ForeignKey(Ubication, on_delete=models.CASCADE)
    type = models.ForeignKey(TypeProject, on_delete=models.CASCADE)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)

    def __str__(self):
        return f'{self.title}'
