from django.contrib import admin
from django.urls import path, include
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView
from users.views.verify_token_view import VerifyTokenView

urlpatterns = [
    path('admin/', admin.site.urls),

    #Simple JWT
    path('login/', TokenObtainPairView.as_view()),
    path('refresh/', TokenRefreshView.as_view()),
    path('verify-token/', VerifyTokenView.as_view()),

    #Users
    path('users/', include('users.urls')),

    #Programs
    path('programs/', include('programs.urls')),
]
