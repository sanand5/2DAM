# -*- coding: utf-8 -*-
# from odoo import http


# class CommentClassifier(http.Controller):
#     @http.route('/comment_classifier/comment_classifier', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/comment_classifier/comment_classifier/objects', auth='public')
#     def list(self, **kw):
#         return http.request.render('comment_classifier.listing', {
#             'root': '/comment_classifier/comment_classifier',
#             'objects': http.request.env['comment_classifier.comment_classifier'].search([]),
#         })

#     @http.route('/comment_classifier/comment_classifier/objects/<model("comment_classifier.comment_classifier"):obj>', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('comment_classifier.object', {
#             'object': obj
#         })

