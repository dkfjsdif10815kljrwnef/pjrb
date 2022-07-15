/**
 * @license Copyright (c) 2003-2020, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// html 태그 허용
	config.allowedContent = true;
	config.extraAllowedContent = '*(*);*{*}';
	CKEDITOR.dtd.$removeEmpty['i'] = false;
	// 줄바꿈 p에서 br로 바꿈
	config.enterMode = CKEDITOR.ENTER_BR;
	//자동 공백문자 추가 방지
	config.fillEmptyBlocks = false;
	config.height = 400;  
	config.filebrowserUploadMethod = 'form';
	
	config.toolbarGroups = [
		{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
		{ name: 'forms', groups: [ 'forms' ] },
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
		{ name: 'links', groups: [ 'links' ] },
		{ name: 'insert', groups: [ 'insert' ] },
		{ name: 'styles', groups: [ 'styles' ] },
		{ name: 'colors', groups: [ 'colors' ] },
		{ name: 'tools', groups: [ 'tools' ] },
		{ name: 'others', groups: [ 'others' ] },
		{ name: 'about', groups: [ 'about' ] }
	];

	config.removeButtons = 'About,Save,NewPage,Templates,Scayt,HiddenField,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,SelectAll,PasteText,PasteFromWord,CreateDiv,Outdent,Indent,Blockquote,BidiLtr,BidiRtl,Language,Anchor,Flash,PageBreak,Iframe,ShowBlocks,Maximize';
};


