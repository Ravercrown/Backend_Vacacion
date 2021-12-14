from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer


from users.models import User
from users.serializers.users_serializers import UserListSerializer, UserSerializer

""" user_list_and_create_view
    : mediante decorador api_view recibe las peticiones GET y POST
    : cuando es GET muestra la lista de los usuarios creados
    : (solamente lista algunos valores)
    : cuando es POST ejecuta la creación de un usuario normal
    : a partir de la data suministrada por el usuario
    : si se presenta algun error, este se hace visible en pantalla
"""
@api_view(['GET','POST'])
def user_list_and_create_view(request):

    #List
    if request.method == 'GET':
        users = User.objects.values('id', 'username', 'email', 'name', 'last_name', 'role', 'password')
        users_serializer = UserListSerializer(users, many = True)
        return Response(users_serializer.data, status=status.HTTP_200_OK)

    #Create
    elif request.method == 'POST':
        users_serializer = UserSerializer(data = request.data)
        #Validation
        if users_serializer.is_valid(raise_exception=True):
            users_serializer.save()

            tokenData = {"username": request.data["username"],
                         "password": request.data["password"]}
            tokenSerializer = TokenObtainPairSerializer(data=tokenData)
            tokenSerializer.is_valid(raise_exception=True)

            return Response(tokenSerializer.validated_data, status=status.HTTP_201_CREATED)
        return Response(users_serializer.errors, status=status.HTTP_400_BAD_REQUEST)


""" user_detail_view
    : Trae la información del usuario según su código de id
    : Al recibir una petición PUT reemplaza la
    : data consultada con la nueva data del request
    : sino logra hacer la modificació
    n muestra en pantalla el error
    : Al recibir una petición DELETE elimina la información
    : del usuario consultado
"""
@api_view(['GET','PUT','DELETE'])
def user_detail_view(request, pk=None):
    user = User.objects.filter(id = pk).first()
    
    if user:

        #Retrieve
        if request.method == 'GET':
            user_serializer = UserSerializer(user)
            return Response(user_serializer.data, status=status.HTTP_200_OK)

        #Update
        elif request.method == 'PUT':
            user_serializer = UserSerializer(user, data=request.data)
            if user_serializer.is_valid():
                user_serializer.save()
                return Response(user_serializer.data, status=status.HTTP_200_OK)
            return Response(user_serializer.errors, status=status.HTTP_400_BAD_REQUEST)

        # Delete
        elif request.method == 'DELETE':
            user.delete()
            return Response({'message': 'Usuario eliminado correctamente'}, status=status.HTTP_200_OK)
    return Response({'message': 'No se encontró un usuario con esos datos'}, status=status.HTTP_400_BAD_REQUEST)