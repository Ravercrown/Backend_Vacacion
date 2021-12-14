from django.urls import path
from users.views.users_views import user_list_and_create_view, user_detail_view

urlpatterns = [
    path('list-create/', user_list_and_create_view),
    path('retrieve-update-destroy/<int:pk>/', user_detail_view),
]