from django.urls import path

from programs.views.programs_views import ProjectList, ProjectCreate, ProjectRetrieveUpdateDestroy, ProjectCount

urlpatterns = [
    path('list/', ProjectList.as_view()),
    path('create/', ProjectCreate.as_view()),
    path('retrieve-update-destroy/<int:pk>', ProjectRetrieveUpdateDestroy.as_view()),
    path('count/', ProjectCount.as_view())
]