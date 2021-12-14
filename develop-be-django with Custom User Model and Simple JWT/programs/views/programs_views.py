from rest_framework import generics, views, status
from rest_framework.response import Response

from programs.serializers.programs_serializers import ProjectSerializer, ProjectCreateSerializer
from programs.models import Project

class ProjectList(generics.ListAPIView):
    queryset = Project.objects.all()
    serializer_class = ProjectSerializer

class ProjectCreate(generics.CreateAPIView):
    queryset = Project.objects.all()
    serializer_class = ProjectCreateSerializer

class ProjectRetrieveUpdateDestroy(generics.RetrieveUpdateDestroyAPIView):
    queryset = Project.objects.all()
    serializer_class = ProjectSerializer


class ProjectCount(views.APIView):

    def get(self, request):
        queryset = Project.objects.all()
        count_users = len(queryset)
        data = {'Cantidad de proyectos registrados': count_users}
        return Response(data=data, status=status.HTTP_200_OK)
