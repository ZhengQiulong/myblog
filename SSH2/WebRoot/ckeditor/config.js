/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.image_previewText = ' '; // 去掉图片预览中的鸟语，这里注意里面一定要有个空格  
    config.filebrowserUploadUrl = "uploadfile-savefile.action"; // 指定上传的目标地址  
    config.width = "900"; //文本域宽度
    config.height = "600";//文本域高度
};
