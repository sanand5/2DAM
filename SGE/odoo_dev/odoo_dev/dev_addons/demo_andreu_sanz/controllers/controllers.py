# -*- coding: utf-8 -*-
# from odoo import http


# class DemoAndreuSanz(http.Controller):
#     @http.route('/demo_andreu_sanz/demo_andreu_sanz', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/demo_andreu_sanz/demo_andreu_sanz/objects', auth='public')
#     def list(self, **kw):
#         return http.request.render('demo_andreu_sanz.listing', {
#             'root': '/demo_andreu_sanz/demo_andreu_sanz',
#             'objects': http.request.env['demo_andreu_sanz.demo_andreu_sanz'].search([]),
#         })

#     @http.route('/demo_andreu_sanz/demo_andreu_sanz/objects/<model("demo_andreu_sanz.demo_andreu_sanz"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('demo_andreu_sanz.object', {
#             'object': obj
#         })
