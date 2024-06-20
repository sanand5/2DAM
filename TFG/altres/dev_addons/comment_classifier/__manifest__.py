{
    'name': 'Comment Classifier',
    'summary': 'Module for classifying comments',
    'author': 'Andreu Sanz Sanz',
    'website': "https://solterraenergia.com/",
    
    'category': 'Tools',
    'version': '1.0',
    
    'depends': ['base'],
    'data': [
        'security/ir.model.access.csv',
        'views/comment_views.xml',
        'views/tag_views.xml',
        'views/analysis_views.xml',
        'views/dataset_views.xml',
        'views/menu.xml',
    ],
    
    'demo': [
        'demo/demo.xml',
    ],
    'application': True,
}
