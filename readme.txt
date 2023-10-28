1.	Este proyecto es una copia del proyecto anterior 'ControlClientesSpring-crud8-security-users-bbdd'. En este proyecto
	vamos a poner en práctica una mejora en el aspecto visual con Bootstrap + Font Awesome.

2.	Agregamos las librerías de Bootstrap a nuestro archivo pom.xml:

		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>bootstrap</artifactId>
			<version>4.4.1-1</version>
		</dependency>
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>font-awesome</artifactId>
			<version>5.12.0</version>
		</dependency>
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>webjars-locator</artifactId>
			<version>0.38</version>
		</dependency>

3.	Abrimos nuestro archivo plantilla.html e importamos las librerías de Bootstrap dentro de la etiqueta de <head>:
	
		<link rel="stylesheet" th:href="@{/webjars/bootstrap/css/bootstrap.min.css}" />
		<link rel="stylesheet" th:href="@{/webjars/font-awesomw/css/all.css}" />
		<script th:src="@{/webjars/jquery/jquery.min.js}"></script>
		<script th:src="@{/webjars/popper.js/umd/popper.min.js}"></script>
		<script th:src="@{/webjars/bootstrap/js/bootstrap.min.js}"></script>

4.	Añadimos en nuestro archivo plantilla.html una barra de navegación <nav>..</nav> en la etiqueta <head>..</head>.
5.	Añadimos en nuestro archivo plantilla.html una sección <section>..</section> en la etiqueta <head>..</head>.
6.	Editamos en nuestro archivo plantilla.html nuestra etiqueta de <footer>..</footer>.
7.	Ahora editamos nuestra página de login.html, añadiendo dos secciones <section>..</section>, una para añadir
	botones y otra para el login.
8.	Ahora editamos nuestra página de index.html.
	Agregamos en templates/layout la página botonesAgregar.html.
	Agregamos en templates/layout la página agregarCliente.html.
	Agregamos en templates/layout la página listadoClientes.html.

9.	Editamos la página botonesAgregar.html.
10.	Desde MySQL Workbench añadimos un campo más sobre la tabla de Persona del esquema 'test', con clic derecho sobre la
	entidad Persona --> Alter Table..., y añadimos el campo de 'saldo'. Rellenamos los registros existentes con valores de
	saldo.

11.	Modificamos nuestra clase de entidad Persona, para añadir el nuevo atributo de 'saldo'.
12.	Ahora modificamos el archivo listadoClientes.html.
13.	Añadimos dos nuevas variables en nuestro controlador para compartir en la página de listadoClientes.html, los valores
	del saldo total de las personas y el número total de personas.
14.	Ahora, editamos el archivo agregarCliente.html para permitir de forma modal la agregación de nuevas personas.
15.	Ahora, editamos el archivo modificar.html para adaptarlo a Bootstrap.
16.	Agregamos en templates/layout la página editarCliente.html.
17.	Agregamos en templates/layout la página botonesEditar.html.









4.	Creamos un nuevo paquete mx.com.gm.util, y dentro creamos la clase EncriptarPassword.java.
5.	Dentro de la clase EncriptarPassword.java añadimos el método "encriptarPassword".
6.	Ejecutamos el método "main" de la clase, copiamos el valor devuelto en consola, en 'password encriptado':

	password = 123
	password encriptado = $2a$10$pmC00N4L/hUPRB60UwXnYevvU.i4p092W0Qa6TNvsgZjWO9spWEbu

7.	Copiamos el valor anterior de "password encriptado" y lo pegamos/llevamos a la tabla de "usuario3", atributo password en
	MySQL Workbench, sustituyendo el valor "123" que teníamos del password.
	
8.	Añadimos la nuevas entidades creadas "usuario3" y "rol" en el paquete mx.com.gm.domain.

9.	Ahora definimos la clase DAO de UsuarioDao en el paquete mx.com.gm.dao, y añadimos un método de seguridad de
	Spring-Security llamado 'findByUsername'.

10.	Ahora creamos la clase de servicio UsuarioService en el paquete mx.com.gm.servicio, e implementamos/sobreescribimos el
	método 'loadUserByUsername' de la interfaz UserDetailsService.

11. Modificamos ahora la clase SecurityConfig.java del paquete mx.com.gm.web, ya que ahora no vamos a crear usuarios en
	memoria sino que vamos a recuperarlos de BBDD.