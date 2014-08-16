package com.wallmart.exception;


@SuppressWarnings( "all" )
public class DAOException extends RuntimeException
{
    private static final long serialVersionUID = -7509998033484813745L;

    /**
     * Nome da DAO que ocorreu a exception
     */

    private Class clazz;

    private String message;

    public DAOException( Exception ex, Class classe, String message )
    {
        super( ex.getLocalizedMessage() );
        super.setStackTrace( ex.getStackTrace() );
        this.clazz = classe;
        this.message = message;

    }

    public DAOException( Class classe, String message )
    {
        this.clazz = classe;
        this.message = message ;

    }

    public DAOException( Exception ex, String message )
    {
        super( ex.getLocalizedMessage() );
        super.setStackTrace( ex.getStackTrace() );
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    /**
     * @return the clazz
     */
    public Class getClazz()
    {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     */
    public void setClazz( Class clazz )
    {
        this.clazz = clazz;
    }
}
