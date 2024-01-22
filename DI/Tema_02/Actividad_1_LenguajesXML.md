# Actividad 1. Lenguajes XML
##### Andreu Sanz Sanz | 2DAM
## SVG (Scalable Vector Graphics):
<div style="text-align: justify;">
SVG es un lenguaje XML, parecido a XHTML, el cual puede ser usado para dibujar gráficos vectoriales, como los mostrados a la derecha. Puede ser usado para crear una imagen ya sea especificando todas las líneas y formas necesarias, modificando las imágenes matriciales (raster images) o una combinación de ambas. La imagen y sus componentes pueden ser transformados, compuestos o filtrados para cambiar completamente su apariencia.
</div>


**Ejemplo:**
```svg
<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100">
  <circle cx="50" cy="50" r="40" stroke="black" stroke-width="3" fill="red" />
</svg>
```
**Visualización:**  
<svg xmlns="http://www.w3.org/2000/svg" width="100" height="70">
  <circle cx="40" cy="40" r="30" stroke="black" stroke-width="3" fill="red" />
</svg>

## XHTML (Extensible Hypertext Markup Language)
<div style="text-align: justify;">
XHTML (Extensible Hypertext Markup Language) es una versión más estricta y XML-compatible de HTML. Combina las reglas de XML con la sintaxis de HTML para garantizar una mayor coherencia en la estructura y presentación de las páginas web. XHTML facilita la transición de documentos HTML a XML, promoviendo una codificación más rigurosa y facilitando la interoperabilidad con otras tecnologías basadas en XML.
</div>

**Ejemplo:**

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3c.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;
charset=utf-8" />
<title>Título de la web</title>
</head>
<body>
<p>Aquí va el contenido</p>
</body>
</html>

```

## XSLT (Extensible Stylesheet Language Transformations):
<div style="text-align: justify;">
Se utiliza para transformar documentos XML en otros formatos, como HTML o XML alternativo, mediante reglas de transformación definidas en hojas de estilo. Permite la presentación y procesamiento de datos XML de manera dinámica.  
</div>  

``` xsd
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <body>
        <h2>Lista de Elementos</h2>
        <ul>
          <xsl:for-each select="root/element">
            <li><xsl:value-of select="."/></li>
          </xsl:for-each>
        </ul>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>

```

## XML Schema (XSD):
<div style="text-align: justify;">
XML Schema define la estructura y los tipos de datos permitidos en documentos XML, proporcionando esquemas para validar la conformidad de los datos. Es crucial para garantizar la integridad y consistencia de los documentos XML.
</div>

``` xsd
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
  <xs:element name="Libreria">
    <xs:complexType>
      <xs:sequence>
        <xs:element name="Libro" maxOccurs="unbounded">
          <xs:complexType>
            <xs:sequence>
              <xs:element name="Titulo" type="xs:string"/>
              <xs:element name="Autor" type="xs:string"/>
              <xs:element name="Año" type="xs:integer"/>
            </xs:sequence>
            <xs:attribute name="ID" type="xs:integer" use="required"/>
          </xs:complexType>
        </xs:element>
      </xs:sequence>
    </xs:complexType>
  </xs:element>
</xs:schema>
```

## MathML (Mathematical Markup Language):
<div style="text-align: justify;">
Se utiliza para representar expresiones y estructuras matemáticas en documentos XML, permitiendo la inclusión de contenido matemático en páginas web y documentos electrónicos. Facilita la comunicación precisa de información matemática en entornos digitales.  
</div>

Ejemplo:
``` xsd
<math xmlns="http://www.w3.org/1998/Math/MathML">
  <msup>
    <mi>x</mi>
    <mn>2</mn>
  </msup>
</math>
```
**Visualización:**  
 
$x^2$