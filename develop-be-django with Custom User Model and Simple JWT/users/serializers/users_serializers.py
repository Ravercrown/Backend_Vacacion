from rest_framework import serializers
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer

from users.models import User

""" UserSerializer
    : el serializador encripta la contraseña del usuario
    : al crearse o al actualizarse
"""
class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = '__all__'

    def create(self, validated_data):
        user = User(**validated_data)
        user.set_password(validated_data['password'])
        user.save()
        return user
    
    def update(self, instance, validated_data):
        updated_user = super().update(instance,validated_data)
        updated_user.set_password(validated_data['password'])
        updated_user.save()
        return updated_user


""" UserListSerializer
    : define los atributos que se van a mostrar
    : al ejecutar una petición GET de lista
"""
class UserListSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        
    def to_representation(self, instance):
        return {
            'id': instance['id'],
            'username': instance['username'],
            'email': instance['email'],
            'name': instance['name'],
            'last_name': instance['last_name'],
            'role': instance['role'],
            'password': instance['password']
        }

""" CustomUserSerializer
    : Modificación de UserSerializer
    : pinta algunos campos solamente, excluye la contraseña.
"""
class CustomUserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['id', 'username', 'email', 'name', 'last_name']