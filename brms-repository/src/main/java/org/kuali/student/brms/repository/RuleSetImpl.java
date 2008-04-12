package org.kuali.student.brms.repository;

import java.util.List;

public class RuleSetImpl extends ItemImpl implements RuleSet
{
	private List<Rule> rules = null;
	private byte[] compiledRulerSet = null;
	private Object compiledRuleSetObject = null;

	private boolean snapshot = false;
	
	public RuleSetImpl( String uuid, String name )
	{
		super( uuid, name );
	}
	
	public List<Rule> getRules()
	{
		return this.rules;
	}
	
	public void setRules( List<Rule> rules )
	{
		this.rules = rules;
	}
	
	public byte[] getCompiledRuleSet()
	{
		return this.compiledRulerSet;
	}
	
	public void setCompiledRuleSet( byte[] compiledRuleSet )
	{
		this.compiledRulerSet = compiledRuleSet;
	}

	public Object getCompiledRuleSetObject()
	{
		return this.compiledRuleSetObject;
	}
	
	public void setCompiledRuleSetObject( Object compiledRuleSetObject )
	{
		this.compiledRuleSetObject = compiledRuleSetObject;
	}

	public boolean isSnapshot() {
		return snapshot;
	}

	public void setSnapshot(boolean snapshot) {
		this.snapshot = snapshot;
	}
	
}
