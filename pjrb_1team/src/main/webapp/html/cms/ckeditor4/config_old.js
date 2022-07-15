/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	config.fontSize_sizes = '8/8px;9/9px;10/10px;11/11px;12/12px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;26/26px;28/28px;36/36px;48/48px;';
	config.language = "ko";			//언어설정
	config.height = '500px';		//Editor 높이  
	config.resize_enabled = false;
	config.enterMode = CKEDITOR.ENTER_BR;	//엔터키 입력시 br 태그 변경
	config.allowedContent = true;
	config.startupFocus = true;
	config.uiColor = '#EEEEEE';		//색상
	config.menu_subMenuDelay = 0;
	config.toolbarCanCollapse = true;		//툴바가 접히는 기능을 넣을때 사용합니다.
	config.coreStyles_bold = { element : 'b', overrides: 'strong' };
	config.image_previewText = CKEDITOR.tools.repeat( '이 화면에서 보이는 문구는 사용자가 업로드 한 이미지가 실제 화면에 어떻게 배치되는지를 보다 명확히 알 수 있도록 하기 위해 쓰여진 것입니다. 실제 화면에는 나타나지 않습니다', 1 );
	
	config.toolbarGroups = [
		{ name: 'styles', groups: [ 'styles' ] },
		{ name: 'colors', groups: [ 'colors' ] },
		{ name: 'tools', groups: [ 'tools' ] },
		{ name: 'others', groups: [ 'others' ] },
		{ name: 'about', groups: [ 'about' ] },
		{ name: 'document', groups: [ 'document', 'doctools', 'mode' ] },
		{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
		{ name: 'forms', groups: [ 'forms' ] },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
		{ name: 'links', groups: [ 'links' ] },
		{ name: 'insert', groups: [ 'insert', 'Table' ] }
		
	];

	config.filebrowserUploadMethod = 'form';
	config.extraPlugins = 'tabletools';
	config.extraPlugins = 'tableresize';
	config.removePlugins = 'magicline';
	config.removeButtons = 'Cut,Templates,Save,Find,SelectAll,Scayt,Form,CopyFormatting,Replace,Copy,Paste,PasteText,PasteFromWord,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,NumberedList,BulletedList,Indent,Outdent,Blockquote,CreateDiv,BidiLtr,BidiRtl,Language,Anchor,Flash,HorizontalRule,PageBreak,Iframe,Styles,Format,Maximize,ShowBlocks,About,NewPage,Preview,Print';
};

CKEDITOR.dtd.$removeEmpty['span'] = false;