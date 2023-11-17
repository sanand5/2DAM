Public NotInheritable Class AboutBox1

    Private Sub AboutBox1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        ' Establezca el título del formulario.
        Dim ApplicationTitle As String
        If My.Application.Info.Title <> "" Then
            ApplicationTitle = My.Application.Info.Title
        Else
            ApplicationTitle = System.IO.Path.GetFileNameWithoutExtension(My.Application.Info.AssemblyName)
        End If
        Me.Text = String.Format("Acerca de Actividad 13", ApplicationTitle)
        ' Inicialice todo el texto mostrado en el cuadro Acerca de.
        ' TODO: personalice la información del ensamblado de la aplicación en el panel "Aplicación" del 
        '    cuadro de diálogo propiedades del proyecto (bajo el menú "Proyecto").
        Me.LabelProductName.Text = "Actividad 13"
        Me.LabelVersion.Text = "1.3"
        Me.LabelCompanyName.Text = "IES MARIA ENRIQUEZ"
        Me.TextBoxDescription.Text = "Descripción: " & Environment.NewLine & "Una aplicación intuitiva en Visual Basic desarrollada con Visual Studio que te permite cambiar dinámicamente el color de fondo con solo un clic, brindando una experiencia visualmente atractiva y personalizada."
    End Sub
End Class
