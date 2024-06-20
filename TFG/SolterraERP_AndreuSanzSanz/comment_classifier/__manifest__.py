{
    'name': 'Comment Classifier',
    'version': '1.0',
    'summary': 'Module for classifying internet comments with multi-label tags',
    'category': 'Tools',
    'author': 'Your Name',
    'depends': ['base'],
    'data': [
        'security/ir.model.access.csv',
        'views/dataset_views.xml',
        'views/tag_views.xml',
        'views/comment_views.xml',
        'views/analysis_views.xml',
    ],
    'application': True,
}
