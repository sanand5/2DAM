# -*- coding: utf-8 -*-
{
    'name': "Sale Order Versions",
    'summary': "Manage versions of sale orders",
    'description': "This module allows creating and managing different versions of sale orders.",
    'author': "Andreu Sanz Sanz",
    'website': "https://solterraenergia.com/",
    
    'category': 'Sales',
    'version': '1.0',

    'depends': ['base', 'sale_management'],
    'data': [
        'security/ir.model.access.csv',
        'views/views.xml',
        'views/templates.xml',
    ],
    'demo': [
        'demo/demo.xml',
    ],
}

