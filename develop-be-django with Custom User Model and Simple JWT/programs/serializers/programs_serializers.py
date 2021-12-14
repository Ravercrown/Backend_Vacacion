from rest_framework import serializers

from programs.models import Project, TypeProject, Ubication
from users.serializers.users_serializers import CustomUserSerializer

class TypeProjectSerializer(serializers.ModelSerializer):
    class Meta: 
        model = TypeProject
        fields = ['type']

class UbicationSerializer(serializers.ModelSerializer):
    class Meta:
        model = Ubication
        fields = ['title']

class ProjectSerializer(serializers.ModelSerializer):
    ubication = UbicationSerializer()
    type = TypeProjectSerializer()
    user = CustomUserSerializer()

    class Meta:
        model = Project
        fields = [
            'id',
            'title',
            'description',
            'value_total',
            'units',
            'expected_utility',
            'duration',
            'value_unit',
            'ubication',
            'type',
            'user'
        ]

class ProjectCreateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Project
        fields = '__all__'
