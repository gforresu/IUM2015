package com.example.giacomo.studymate;

import java.util.Date;

/**
 * La classe rappresenta un evento impostato dall'utente
 *
 */
public class Event
{
    private String eventName;
    private Date startDate;
    private Date endDate;
    private String eventType; //Evento esame od altro evento
    private int cfu;
    private int eventId; /*identificatore. Può tornare utile ...ad esempio nel costruttore
                        WeekViewEvent() il primo parametro è l'ID dell'evento */
    private int complexity; //grado di complessiyà

    /**
     * Costruttore per evento esame
     *
     * @param eventName nome dell'esame
     * @param startDate inizio evento
     * @param endDate data fine
     * @param eventType tipologia evento
     * @param eventId id
     * @param complexity complessità dell'esame
     * @param cfu Crediti
     *
     * */
    public Event(String eventName, Date startDate, Date endDate, String eventType, int eventId, int complexity, int cfu)
    {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventType = eventType;
        this.eventId = eventId;
        this.complexity = complexity;
        this.cfu = cfu;
    }

    public Event(String eventName, Date startDate, Date endDate, String eventType, int eventId)
    {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventType = eventType;
        this.eventId = eventId;
    }


    public String getEventName()
    {
        return eventName;
    }

    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public String getEventType()
    {
        return eventType;
    }

    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    public int getCfu()
    {
        return cfu;
    }

    public void setCfu(int cfu)
    {
        this.cfu = cfu;
    }


    public int getEventId()
    {
        return eventId;
    }

    public void setEventId(int eventId)
    {
        this.eventId = eventId;
    }


    public int getComplexity()
    {
        return complexity;
    }

    public void setComplexity(int complexity)
    {
        this.complexity = complexity;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        //if (eventId != event.getEventId()) return false; //Verificare se è meglio controllare o meno l'ID
        if (!endDate.equals(event.getEndDate())) return false;
        if (!eventName.equals(event.getEventName())) return false;
        if (!eventType.equals(event.getEventType())) return false;
        if (!startDate.equals(event.getStartDate())) return false;
        if (complexity != event.getComplexity()) return false;
        if (cfu != event.getCfu()) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = eventName.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + eventType.hashCode();
        result = 31 * result + eventId;
        result = 31 * result + cfu;
        return result;
    }
}
