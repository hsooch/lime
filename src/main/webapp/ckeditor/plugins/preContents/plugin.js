CKEDITOR.plugins.add( 'preContents', // CKEditor에 이벤트 내용을 추가 합니다.

		{

		        init: function( editor )

		        {

		                editor.addCommand( 'preContents_event', {

		                        exec : function (editor) {
		                        	window.open('/ckeditor/pop_preview.jsp','preview','width=850,height=500, scrollbars=yes, resizable=yes ');
		                                // 여기에 버튼 클릭 시 동작할 내용에 대해서 작성합니다.
		                        }

		                });

		                editor.ui.addButton( 'preContents', // toolbar에 작성한 버튼 내용 입니다.

		                {

		                        label: '미리보기', // 마우스 over 시 보여지는 내용 입니다.

		                        command: 'preContents_event', // 클릭 시 이벤트 입니다.

		                        icon: this.path + 'icons/icon.png' // 버튼의 이미지 입니다.

		                });

		        }

		});

