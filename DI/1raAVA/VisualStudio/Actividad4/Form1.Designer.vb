<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class Form1
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()>
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()>
    Private Sub InitializeComponent()
        mv_btn = New Button()
        SuspendLayout()
        ' 
        ' mv_btn
        ' 
        mv_btn.Font = New Font("Segoe UI", 15F, FontStyle.Regular, GraphicsUnit.Point)
        mv_btn.Location = New Point(12, 216)
        mv_btn.Name = "mv_btn"
        mv_btn.Size = New Size(452, 44)
        mv_btn.TabIndex = 0
        mv_btn.Text = "Pulsa aquí para saber el significado de la vida"
        mv_btn.UseVisualStyleBackColor = True
        ' 
        ' Form1
        ' 
        AutoScaleDimensions = New SizeF(7F, 15F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(476, 272)
        Controls.Add(mv_btn)
        Name = "Form1"
        Text = "¿Cuál el es significado de la Vida?"
        ResumeLayout(False)
    End Sub

    Friend WithEvents mv_btn As Button
End Class
