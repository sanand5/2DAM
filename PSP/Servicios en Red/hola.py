from PIL import Image
import os

def convertir_heic_a_png(ruta_heic, ruta_png):
    try:
        # Abrir la imagen HEIC
        imagen_heic = Image.open(ruta_heic)
        
        # Guardar la imagen en formato PNG
        imagen_heic.save(ruta_png, format="PNG")
        
        print(f"La imagen '{ruta_heic}' se ha convertido exitosamente a PNG.")
    except Exception as e:
        print(f"Error al convertir la imagen '{ruta_heic}': {e}")

def convertir_carpeta_heic_a_png(ruta_carpeta_entrada, ruta_carpeta_salida):
    # Verificar si la carpeta de salida existe, si no, crearla
    if not os.path.exists(ruta_carpeta_salida):
        os.makedirs(ruta_carpeta_salida)
    
    # Obtener lista de archivos en la carpeta de entrada
    archivos = os.listdir(ruta_carpeta_entrada)
    
    # Iterar sobre los archivos
    for archivo in archivos:
        # Verificar si el archivo es un HEIC
        if archivo.endswith(".heic"):
            ruta_heic = os.path.join(ruta_carpeta_entrada, archivo)
            # Generar ruta de salida en la carpeta de salida con la misma estructura
            ruta_png = os.path.join(ruta_carpeta_salida, archivo.replace(".heic", ".png"))
            # Convertir el archivo HEIC a PNG
            convertir_heic_a_png(ruta_heic, ruta_png)

def main():
    # Ruta de la carpeta con las imágenes HEIC
    ruta_carpeta_entrada = "/home/vboxuser/Documentos/tot/"  # Reemplaza esto con la ruta de tu carpeta de entrada
    # Ruta de la carpeta para guardar las imágenes convertidas
    ruta_carpeta_salida = "/home/vboxuser/Documentos/heic"    # Reemplaza esto con la ruta de tu carpeta de salida
    
    # Verificar si la carpeta de entrada existe
    if not os.path.exists(ruta_carpeta_entrada):
        print(f"La carpeta de entrada '{ruta_carpeta_entrada}' no existe.")
        return
    
    # Convertir todas las imágenes HEIC a PNG en la carpeta de entrada
    convertir_carpeta_heic_a_png(ruta_carpeta_entrada, ruta_carpeta_salida)

if __name__ == "__main__":
    main()
