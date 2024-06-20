

# Actualizar y actualizar todos los paquetes
sudo apt update
sudo apt upgrade -y

# Instalar PostgreSQL y comprobar su estado
sudo apt install postgresql -y
sudo systemctl status postgresql

# Acceder a la consola de PostgreSQL y luego salir
sudo -u postgres psql
\q

# Instalar psycopg2 para la conexión a PostgreSQL desde Python
sudo apt install python3-psycopg2 -y

# Instalar wkhtmltopdf
sudo apt install wkhtmltopdf -y

# Instalar librerías Python xlwt y num2words
sudo pip3 install xlwt
sudo pip3 install num2words

# Verificar las instalaciones de xlwt y num2words
pip3 list | grep xlwt
pip3 list | grep num2words

# Configurar claves y repositorio de Odoo
wget -q -O - https://nightly.odoo.com/odoo.key | sudo gpg --dearmor -o /usr/share/keyrings/odoo-archive-keyring.gpg
echo 'deb [signed-by=/usr/share/keyrings/odoo-archive-keyring.gpg] https://nightly.odoo.com/17.0/nightly/deb/ ./' | sudo tee /etc/apt/sources.list.d/odoo.list

# Actualizar e instalar Odoo
sudo apt update && sudo apt install odoo

# Permitir acceso a los puertos necesarios en UFW
sudo ufw allow 5432
sudo ufw allow 8069
sudo ufw status numbered

# Verificar la instalación de Odoo
sudo dpkg -l odoo
sudo systemctl status odoo