بناء تطبيق أندرويد (APK) مجاناً من غير ما تنزّل أي برنامج
=========================================================
1) اعمل حساب مجاني على github.com ثم اضغط New repository، سمّيه khatwa واختار Public ثم Create.
2) اضغط "uploading an existing file" وارفع كل محتويات الفولدر ده (app و gradle.properties و build.gradle و settings.gradle). ملف BUILD-WORKFLOW.yml مش هترفعه هنا. اضغط Commit.
3) من نفس الريبو: Add file ثم Create new file، واكتب في خانة الاسم بالظبط:  .github/workflows/build.yml
   وانسخ جواه كل محتوى ملف BUILD-WORKFLOW.yml. اضغط Commit.
4) افتح تبويب Actions. هتلاقي "Build APK" شغّال (٣-٥ دقايق). لو ما اشتغلش اضغط Run workflow.
5) لما يخلص (علامة خضراء) افتحه ونزّل khatwa-apk من تحت (Artifacts). فك الضغط هتلاقي app-debug.apk.
6) انقل الملف للموبايل وافتحه. لو طلب تسمح بالتثبيت من مصدر غير معروف، وافق.
التطبيق ده لأندرويد فقط.
