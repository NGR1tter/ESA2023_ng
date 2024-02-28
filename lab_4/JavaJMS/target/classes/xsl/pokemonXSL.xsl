<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" omit-xml-declaration="yes"/>
    <xsl:template match="/">
        <div class="links">
            <div align="center">
                <h1>Pokemons</h1>
            </div>
        </div>
        <div class="pokemon-input" align="center" valign="middle" style=" margin-left: 50px;">
            <table border="1" style="margin-top: 5px">
                <tr bgcolor="#CCCCCC">
                    <td>
                        <strong>Pokemon ID</strong>
                    </td>
                    <td>
                        <strong>Name</strong>
                    </td>
                    <td>
                        <strong>Attack</strong>
                    </td>
                    <td>
                        <strong>Life</strong>
                    </td>
                </tr>
                <xsl:for-each select="ArrayList/item">
                    <tr>
                        <td>
                            <xsl:value-of select="id"/>
                        </td>
                        <td>
                            <xsl:value-of select="name"/>
                        </td>
                        <td>
                            <xsl:value-of select="attack"/>
                        </td>
                        <td>
                            <xsl:value-of select="life"/>
                        </td>
                    </tr>
                </xsl:for-each>
            </table>
        </div>
        <div align="center">
            <p>
                <b>
                    <a href="/api/coach/xsl">Coach</a>
                </b>
            </p>
        </div>
    </xsl:template>
</xsl:stylesheet>