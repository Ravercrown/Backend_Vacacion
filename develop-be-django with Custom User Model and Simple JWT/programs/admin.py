from django.contrib import admin

from programs.models import Project, TypeProject, Ubication

admin.site.register(TypeProject)
admin.site.register(Ubication)
admin.site.register(Project)
