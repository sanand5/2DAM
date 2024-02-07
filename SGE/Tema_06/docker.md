# Comands
### cmd 1: Comando instlar Docker Hello-Word
`docker pull hello-world` o  `doker run hello-world`
### cmd 2: Crear contenedor para el Docker Hello-Word
`docker run -d --name=helloAndreu hello-world`
### cmd 3: Descargar la imaguen y contenedor de postgres
`docker run -d --restart="always" -e POSTGRES_USER=odoo -e POSTGRES_PASSWORD=odoo -e POSTGRES_DB=postgres --name db postgres:latest`
### cmd 4: Descargar la imaguen y contenedor de Odoo
`docker run -p 8069:8069 --name odoo --link db:db -t odoo`
### cmd 5: 
`docker run -d -v odoo-db:/var/lib/postgresql/data -e POSTGRES_USER=odoo -e POSTGRES_PASSWORD=odoo -e POSTGRES_DB=postgres --name db postgres:latest`
### cmd 6: 
`docker run -v odoo-data:/var/lib/odoo -d -p 8069:8069 --name odoo --link db:db -t odoo:latest`