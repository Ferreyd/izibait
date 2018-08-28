<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'matches.label', default: 'Matches')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-matches" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-matches" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            %{--<f:table collection="${matchesList}" />--}%

            <table class="table table-striped">
                <thead>
                <tr>
                    <g:sortableColumn property="Date" title="Date"/>
                    <g:sortableColumn property="Equipe1" title="Equipe1"/>
                    <g:sortableColumn property="Equipe2" title="Equipe2"/>
                    <g:sortableColumn property="Resultat" title="Resultat"/>
                    <g:sortableColumn property="Cote1" title="Cote1"/>
                    <g:sortableColumn property="Cote2" title="Cote2"/>
                    <g:sortableColumn property="CoteEgalite" title="CoteEgalite"/>
                    <g:sortableColumn property="MaxDelta" title="Max Delta"/>
                    <g:sortableColumn property="ResultatParie" title="Resultats Parié"/>
                </tr>
                </thead>
                <tbody>
                <g:each in="${selectedMatch}" status="i" var="match">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                        <td><g:formatDate format ="dd-MM-yyyy" style="SHORT" date="${match.date}" /></td>
                        <td>${match.team1}</td>
                        <td>${match.team2}</td>
                        <td>${match.outcome}</td>
                        <td>${match.oddTeam1}</td>
                        <td>${match.oddTeam2}</td>
                        <td>${match.oddDraw}</td>
                        <td>${match.maxDelta}</td>
                        <td>${match.bettingResult}</td>

                    </tr>
                </g:each>
                </tbody>
            </table>



            <div class="pagination">
                <g:paginate total="${matchesCount ?: 0}" />
            </div>
            <div>
                <g:link class="btn btn-primary" action="loadJson">Load Json</g:link>
            </div>
        </div>
    </body>
</html>