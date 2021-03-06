package tss.tpm;

import tss.*;


// -----------This is an auto-generated file: do not edit

//>>>

/**
 *  This is the format for each of the authorizations in the session area of the response. If
 *  the TPM returns TPM_RC_SUCCESS, then the session area of the response contains the same
 *  number of authorizations as the command and the authorizations are in the same order.
 */
public class TPMS_AUTH_RESPONSE extends TpmStructure
{
    /** the session nonce, may be the Empty Buffer */
    public byte[] nonce;
    
    /** the session attributes */
    public TPMA_SESSION sessionAttributes;
    
    /** either an HMAC or an EmptyAuth */
    public byte[] hmac;
    
    public TPMS_AUTH_RESPONSE() {}
    
    @Override
    public void toTpm(OutByteBuf buf) 
    {
        buf.writeInt(nonce != null ? nonce.length : 0, 2);
        if (nonce != null)
            buf.write(nonce);
        sessionAttributes.toTpm(buf);
        buf.writeInt(hmac != null ? hmac.length : 0, 2);
        if (hmac != null)
            buf.write(hmac);
    }

    @Override
    public void initFromTpm(InByteBuf buf)
    {
        int _nonceSize = buf.readInt(2);
        nonce = new byte[_nonceSize];
        buf.readArrayOfInts(nonce, 1, _nonceSize);
        int _sessionAttributes = buf.readInt(1);
        sessionAttributes = TPMA_SESSION.fromInt(_sessionAttributes);
        int _hmacSize = buf.readInt(2);
        hmac = new byte[_hmacSize];
        buf.readArrayOfInts(hmac, 1, _hmacSize);
    }

    @Override
    public byte[] toTpm() 
    {
        OutByteBuf buf = new OutByteBuf();
        toTpm(buf);
        return buf.getBuf();
    }

    public static TPMS_AUTH_RESPONSE fromTpm (byte[] x) 
    {
        TPMS_AUTH_RESPONSE ret = new TPMS_AUTH_RESPONSE();
        InByteBuf buf = new InByteBuf(x);
        ret.initFromTpm(buf);
        if (buf.bytesRemaining()!=0)
            throw new AssertionError("bytes remaining in buffer after object was de-serialized");
        return ret;
    }

    public static TPMS_AUTH_RESPONSE fromTpm (InByteBuf buf) 
    {
        TPMS_AUTH_RESPONSE ret = new TPMS_AUTH_RESPONSE();
        ret.initFromTpm(buf);
        return ret;
    }

    @Override
    public String toString()
    {
        TpmStructurePrinter _p = new TpmStructurePrinter("TPMS_AUTH_RESPONSE");
        toStringInternal(_p, 1);
        _p.endStruct();
        return _p.toString();
    }

    @Override
    public void toStringInternal(TpmStructurePrinter _p, int d)
    {
        _p.add(d, "byte", "nonce", nonce);
        _p.add(d, "TPMA_SESSION", "sessionAttributes", sessionAttributes);
        _p.add(d, "byte", "hmac", hmac);
    }
}

//<<<

