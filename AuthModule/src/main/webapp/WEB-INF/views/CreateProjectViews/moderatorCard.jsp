<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${moderator.modFamily}
${moderator.modFirstName}
${moderator.modSecName}
<a href="/inviteAdminClient?modId=${moderator.modId}&prId=${projectId}">Вернуться</a>
</body>
</html>
